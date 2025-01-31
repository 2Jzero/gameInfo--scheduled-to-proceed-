package com.project.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// <테이블과 매핑되는 엔티티 클래스, primary key의 타입>
@Repository
public interface GuardLibJpa extends JpaRepository<GuardJPA, Integer> {

    // db의 스케줄 리스트 화면에 보여줌
    //@Query(value = "select ds_sq, ds_no, ds_title, ds_ox, ds_owner, ds_point from daily_schedule where ds_owner = :userId",  nativeQuery = true)
    //public List<GuardJPA> dailyList(@Param("userId") String userId);

}
