package com.mycompany.proyectosia.user;


import com.mycompany.proyectosia.utils.EncryptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserCrontroller {
    @Autowired private UserService service;
    EncryptPassword hash= new EncryptPassword();
    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers= service.listAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForma(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Crear Usuario");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        String passwordHashed= hash.hashPassword(user.getPassword());
        user.setPassword(passwordHashed);
        service.save(user);
        ra.addFlashAttribute("message", "El usuario se ha registrado!!");
        return "redirect:/users";
    }

    @PostMapping("/login")
    public String login(String ci, String password){
        List<User> listUsers= service.listAll();
        for(User user: lisUsers){

        }
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            User user= service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Actualizar Usuario: "+id);

            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "Se elimino al usuario: "+id);
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }


}
