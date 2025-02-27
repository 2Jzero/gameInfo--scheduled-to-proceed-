package com.project.service;

import com.project.dto.BossInfoDTO;
import com.project.jpa.BossJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    private final BossJpaRepository bossJpaRepository;

    @Autowired
    public MainService(BossJpaRepository bossJpaRepository) {
        this.bossJpaRepository = bossJpaRepository;
    }

    public List<BossInfoDTO> mainPage() {
        List<BossInfoDTO> bossList = new ArrayList<>();

        bossList = bossJpaRepository.mainPage(); // 내일 크롤링부터 다시, 구조 다시 짜야됨

        for (BossInfoDTO boss : bossList) {
            boss.processElements(); // 문자열을 리스트로 변환
        }

        return bossList;
    }
}
