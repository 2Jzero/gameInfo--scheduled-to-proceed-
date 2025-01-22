package com.project.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class GuardCrawlingService {

    public String GuardCrawling() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.gtales.top/raids");

        // 모달이 열릴 때까지 기다리기 (예: 10초)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 모달 내의 이미지 요소 찾기
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal")));
        List<WebElement> imagesInModal = modal.findElements(By.tagName("img"));

        String bossImg = "";
        String bossName = "";

        // 각 이미지의 src 속성 출력
        for (WebElement image : imagesInModal) {
            bossName = image.getAttribute("alt");
            bossImg = image.getAttribute("src");
            if (bossImg != null && !bossImg.isEmpty()) {
                System.out.println("보스 이름 : " + bossName);

                System.out.println("보스 이미지 : " + bossImg);
            }
        }

        driver.quit();

        return bossImg;
    }
}

