package com.project.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boss")
@Getter // Builder 이용하여 객체 생성
@Builder // AllArgsConstructor, NoArgsConstructor 같이 이용해야 컴파일 에러 발생 안함
@AllArgsConstructor // 모든 필드 파라미터로 받는 생성자 자동 생성
@NoArgsConstructor // 인자 없는 기본 생성자 자동 생성
@Setter
public class GuardJPA {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
    @Column(name = "boss_id") // 길드보스 순서
    private int bossId;

    // 길드 보스 이름
    @Column(length = 20, name = "boss_name")
    private String bossName;

    // 길드 보스 이미지
    @Column(length = 300, name = "boss_image")
    private String bossImg;

    // boss element 테이블을 연결 (양방향 연결관계)
    @OneToMany(mappedBy = "boss", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default // 초기값 무시 오류 해결
    private List<BossElementJPA> bossElements = new ArrayList<>();
}
