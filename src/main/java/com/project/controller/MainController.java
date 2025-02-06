package com.project.controller;

import com.project.jpa.GuardJPA;
import com.project.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class MainController {

    private final MainService mService;

    @Autowired
    public MainController(MainService mService) {
        this.mService = mService;
    }

    @GetMapping("guardLib")
    public String mainPage(Model model) {

        List<GuardJPA> bossList = mService.mainPage();

        model.addAttribute("bossList", bossList);

        return "guardLib";
    }
}
