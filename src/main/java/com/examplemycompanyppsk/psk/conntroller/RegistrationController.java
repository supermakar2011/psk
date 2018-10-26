package com.examplemycompanyppsk.psk.conntroller;

import com.examplemycompanyppsk.psk.model.Role;
import com.examplemycompanyppsk.psk.model.User;
import com.examplemycompanyppsk.psk.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String reg(){
        return "registration";
    }

    @PostMapping("/registration")
    public String add(User user,Model model ){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null){
            model.addAttribute("message","User exists");
            return "registration";
        }
       user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        userRepo.save(user);
        return "redirect:/login";

    }
}
