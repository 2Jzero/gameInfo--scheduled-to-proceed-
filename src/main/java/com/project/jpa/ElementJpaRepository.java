package com.project.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// <테이블과 매핑되는 엔티티 클래스, primary key의 타입>
@Repository
public interface ElementJpaRepository extends JpaRepository<ElementJPA, Integer> {

}
