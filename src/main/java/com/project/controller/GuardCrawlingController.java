package com.project.controller;

import com.project.service.GuardCrawlingService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.GetMapping;

public class GuardCrawlingController {

    // final 선언해서 불변성 보장받는다.
    private final GuardCrawlingService gService;

    // 생성자를 하나만 추가할 경우 AutoWired 불필요, 그 이상은 필요
    public GuardCrawlingController(GuardCrawlingService gService) {
        this.gService = gService;
    }

    @GetMapping("/GuardCrawling")
    public String GuardCrawling() {
        return gService.GuardCrawling();
    }
}
