package com.project.service;

import com.project.jpa.*;
import jakarta.transaction.Transactional;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
@Transactional
public class GuardCrawlingService {

    private final BossJpaRepository bossJpaRepository;
    private final ElementJpaRepository elementJpaRepository;
    private final BossElementJpaRepository bossElementJpaRepository;

    @Autowired
    public GuardCrawlingService(BossJpaRepository bossJpaRepository, ElementJpaRepository elementJpaRepository,
                                BossElementJpaRepository bossElementJpaRepository) {

        this.bossJpaRepository = bossJpaRepository;
        this.elementJpaRepository = elementJpaRepository;
        this.bossElementJpaRepository = bossElementJpaRepository;

    }

    public String GuardCrawling() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.gtales.top/raids");

        // 모달이 열릴 때까지 기다리기 (예: 10초)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 모달 내의 이미지 요소 찾기
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal")));
        List<WebElement> imagesInModal = modal.findElements(By.tagName("img"));

        // 크롤링한 결과를 저장하기 위한 리스트들
        ArrayList<String> bossNameList = new ArrayList<>();
        ArrayList<String> bossImgList = new ArrayList<>();

        Set<String> bossElementName = new LinkedHashSet<>();
        Set<String> bossElementImg = new LinkedHashSet<>();

        ArrayList<String> bossIsActive = new ArrayList<>();


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
                    bossNameList.add(bossAlt);
                    bossImgList.add(bossImg);
                    //System.out.println("보스 이름 : " + bossAlt + "--- 보스 이미지 : " +  bossImg);
                } else {
                    // img요소의 부모요소, 두칸 올라가서 접근
                    WebElement buttonDiv = image.findElement(By.xpath("..//.."));
                    // 이미지 속성 중 비활성화 속성은 'x'로 추가
                    bossElementName.add(bossAlt);
                    bossElementImg.add(bossImg);
                    if(buttonDiv.getAttribute("class").contains("opacity")) {
                        bossIsActive.add("X");
                        //System.out.println("보스 속성 : " + bossAlt + "--- 속성 이미지 : x");
                    } else {
                        bossIsActive.add("O");
                        //System.out.println("보스 속성 : " + bossAlt + "--- 속성 이미지 : " + "o");
                    }
                }
            }
        }

        // set list에 들어있는 값 순차적으로 가져오기 위한 객체
        Iterator<String> elementNameIterator = bossElementName.iterator();
        Iterator<String> elementImgIterator = bossElementImg.iterator();

        // 밑 반복문에서 사용하기 위함
        List<ElementJPA> elementList = new ArrayList<>();

        for(int i = 0; i < 6; i++) {
            ElementJPA elementJPA = new ElementJPA();

            elementJPA.setElementName(elementNameIterator.next());
            elementJPA.setElementImg(elementImgIterator.next());

            elementJpaRepository.save(elementJPA);
            elementList.add(elementJPA);

        }

        for(int i = 0; i < bossNameList.size(); i++) {
            GuardJPA guardJPA = new GuardJPA();

            guardJPA.setBossName(bossNameList.get(i));
            guardJPA.setBossImg(bossImgList.get(i));

            bossJpaRepository.save(guardJPA);

            for(int m = 0; m < 6; m++) {
                BossElementJPA bossElementJPA = new BossElementJPA();
                bossElementJPA.setIsActive(bossIsActive.get(i * 6 + m)); // 한 보스 묶음을 6개로 두고 계산

                bossElementJPA.setBoss(guardJPA);
                bossElementJPA.setElement(elementList.get(m));

                bossElementJpaRepository.save(bossElementJPA);

            }
        }

//        for(String bossName : bossNameList) {
//            System.out.println("보스 이름 : " + bossName);
//        }
//        for(String bossimage : bossImgList) {
//            System.out.println("보스 이미지 : " + bossimage);
//        }
//        for(String bossele : bossElementName) {
//            System.out.println("보스 속성 이름 : " + bossele);
//        }
//        for(String bosseleImg : bossElementImg) {
//            System.out.println("보스 속성 이미지 : " + bosseleImg);
//        }
//        for(String active : bossIsActive) {
//            System.out.println("보스 속성 유무 : " + active);
//        }

        driver.quit();

        return "";
    }
}

