package com.frontend.train_booking_frontend_customer.controllers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frontend.train_booking_frontend_customer.models.User;
import com.frontend.train_booking_frontend_customer.services.IUserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private HttpSession session;

    @GetMapping("/profile")
    public String profile(Model model) {
        User user = userService.userProfile();
        if (user == null) {
            return "auth/signIn"; 
        }
        model.addAttribute("page", "user")
            .addAttribute("userLogin", user);
        
        watchSession();  

        return "user/profile"; 
    }
    
    public void watchSession() {
        Map<String, Object> sessionInfo = new HashMap<>();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            sessionInfo.put(attributeName, session.getAttribute(attributeName));
        }
        
        System.out.println("session: " + sessionInfo); 
    }
    
    @GetMapping("/user-account-setting/{id}")
    public String userAccountSetting(@PathVariable Integer id, Model model) {
        User user = userService.userProfile();
        if (user == null) {
            return "auth/signIn"; 
        }
        
        model.addAttribute("page", "user")
            .addAttribute("user", user);
        
        return "user/user-account-setting";  
    }
    
    @PostMapping("/updateProfile/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("user") User user, BindingResult result,
                         RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("page", "user");
            return "user/user-account-setting";  
        }

        user.setId(id);
        if (userService.updateUser(user)) {
            redirectAttributes.addFlashAttribute("success", "Profile updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Profile update failed!");
        }
        return "redirect:/user/profile";
    }
}
