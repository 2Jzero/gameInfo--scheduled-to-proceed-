package com.project.jpa;

import com.project.dto.BossInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// <테이블과 매핑되는 엔티티 클래스, primary key의 타입>
@Repository
public interface BossJpaRepository extends JpaRepository<GuardJPA, Integer> {

    // db의 리스트 화면에 보여줌
    @Query(value = "select b.boss_name as bossName, b.boss_image as bossImage, " +
            "e.el_name as elementName, e.el_image as elementImage " +
            "from boss b " +
            "join boss_element be on b.boss_id = be.boss_id " +
            "join element e on  be.element_id = e.el_id ",
            nativeQuery = true)
    public List<BossInfoDTO> mainPage();

}
