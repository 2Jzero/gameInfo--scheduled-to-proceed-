package com.project.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
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

        // 크롤링한 결과를 저장하기 위한 리스트들
        ArrayList<String> bossImgList = new ArrayList<>();
        ArrayList<String> bossElementList = new ArrayList<>();
        ArrayList<String> bossNameList = new ArrayList<>();

        // 크롤링한 결과를 저장하기 위한 변수
        String bossAlt;
        String bossImg;

        // 각 이미지의 src 속성 출력
        for (WebElement image : imagesInModal) {
            // 보스의 이름과 속성 정의, 이거 속성 이름이랑 구분해서 크롤링해야됨
            bossAlt = image.getAttribute("alt");
            // 보스 이미지와 속성 이미지
            bossImg = image.getAttribute("src");
            if (bossImg != null && !bossImg.isEmpty()) {
                // alt에서 보스 이름과 속성을 구분짓기 위해 width로 조건문 구현
                String width = image.getAttribute("width");
                if(width.equals("100")) {
                    System.out.println("보스 이름 : " + bossAlt + "--- 보스 이미지 : " +  bossImg);
                } else {
                    // img요소의 부모요소, 두칸 올라가서 접근
                    WebElement buttonDiv = image.findElement(By.xpath("..//.."));
                    // 이미지 속성 중 비활성화 속성은 'x'로 추가
                    if(buttonDiv.getAttribute("class").contains("opacity")) {
                        System.out.println("보스 속성 : " + bossAlt + "--- 속성 이미지 : x");
                    } else {
                        System.out.println("보스 속성 : " + bossAlt + "--- 속성 이미지 : " + bossImg);
                    }
                }
            }
        }
        driver.quit();

        return "";
    }
}

