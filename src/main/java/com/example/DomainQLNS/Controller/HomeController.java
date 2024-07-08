package com.example.DomainQLNS.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    private HttpServletRequest request;
    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Người dùng đã đăng nhập, chuyển hướng đến trang chính hoặc bất kỳ URL nào phù hợp
            model.addAttribute("httpServletRequest", request);
            return "Home/index";
        }
        // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
        return "redirect:/login";
    }

    @GetMapping("/contact")
    public String contact(){
        return "Home/contact";
    }
}
