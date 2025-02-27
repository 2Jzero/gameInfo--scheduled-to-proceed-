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
    @Query(value = "SELECT b.boss_name AS bossName, b.boss_image AS bossImage, " +
            "GROUP_CONCAT(e.el_name ORDER BY e.el_name SEPARATOR ', ') AS elementNames, " +
            "GROUP_CONCAT(e.el_image ORDER BY e.el_name SEPARATOR ', ') AS elementImages " +
            "FROM boss b " +
            "JOIN boss_element be ON b.boss_id = be.boss_id " +
            "JOIN element e ON be.element_id = e.el_id " +
            "WHERE be.boss_element_is_active = 'O' " +
            "GROUP BY b.boss_id",
            nativeQuery = true)
    List<BossInfoDTO> mainPage();

}
