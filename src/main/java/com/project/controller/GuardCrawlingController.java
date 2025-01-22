package com.project.controller;

import com.project.service.GuardCrawlingService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuardCrawlingController {

    // final 선언해서 불변성 보장받는다.
    private final GuardCrawlingService gService;

    // 생성자를 하나만 추가할 경우 AutoWired 불필요, 그 이상은 필요
    public GuardCrawlingController(GuardCrawlingService gService) {
        this.gService = gService;
    }

    @GetMapping("/guardCrawling")
    public String GuardCrawling(Model model) {
        String imageUrl = gService.GuardCrawling();

        // Model에 이미지 URL을 추가
        model.addAttribute("imageUrl", imageUrl);

        return "guardLib";
    }
}
