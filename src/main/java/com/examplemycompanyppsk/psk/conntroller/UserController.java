package com.examplemycompanyppsk.psk.conntroller;

import com.examplemycompanyppsk.psk.model.Role;
import com.examplemycompanyppsk.psk.model.User;
import com.examplemycompanyppsk.psk.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize(value = "hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users",userRepo.findAll());
        return "listUser";
    }
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "editList";
    }
    @PostMapping()
    public String userSave(@RequestParam(name = "username") String username,
                           @RequestParam Map<String,String> form,
                           @RequestParam(name = "userId")User user){
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values()).map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);

        return "redirect:/user";
    }
}
