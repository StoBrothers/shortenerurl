package com.shorterurl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shorterurl.service.CurrentAccount;

/**
 * Home controller.
 *
 * @author Sergey Stotskiy
 *
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String getHomePage(@ModelAttribute CurrentAccount currentUser) {
        if (currentUser == null) {
            return "redirect:/login";
        }
        return "home";
    }
}
