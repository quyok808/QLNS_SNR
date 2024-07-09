package com.example.DomainQLNS.Controller;

import com.example.DomainQLNS.Models.Response;
import com.example.DomainQLNS.Models.User;
import com.example.DomainQLNS.Service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/account")
    public String showAccountDetail(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        // Kiểm tra nếu người dùng là một đối tượng UserDetails
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            Optional<User> user = userService.findByUsername(userDetails.getUsername());
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("password", user.get().getPassword()); // Không khuyến khích hiển thị mật khẩu
            model.addAttribute("firstname", user.get().getFirstname());
            model.addAttribute("lastname", user.get().getLastname());
            model.addAttribute("phone", user.get().getPhone());
            model.addAttribute("authorities", userDetails.getAuthorities());
        } else {
            model.addAttribute("username", principal.toString());
        }
        return "Account/Detail";
    }

    @PostMapping("/updateUserInfo")
    @ResponseBody
    public Response updateUserInfo(@RequestBody User request) {
        boolean success = userService.updateUserInfo(request);
        return new Response(success);
    }
}
