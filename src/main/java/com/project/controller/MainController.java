package com.project.controller;

import com.project.dto.BossInfoDTO;
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

    // 메인페이지
    @GetMapping("guardLib")
    public String mainPage(Model model) {

        List<BossInfoDTO> bossList = mService.mainPage();

        model.addAttribute("bossList", bossList);

        return "guardLib";
    }
    
    // 보스 공략 팀 정보 페이지
    public String bossAttackTeam() {

        return "guardLib";
    }
}
