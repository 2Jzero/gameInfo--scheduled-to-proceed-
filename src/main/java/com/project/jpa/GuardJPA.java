package com.project.jpa;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "GuildBoss")
@Getter // Builder 이용하여 객체 생성
@Builder // AllArgsConstructor, NoArgsConstructor 같이 이용해야 컴파일 에러 발생 안함
@AllArgsConstructor // 모든 필드 파라미터로 받는 생성자 자동 생성
@NoArgsConstructor // 인자 없는 기본 생성자 자동 생성
@Setter
public class GuardJPA {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
    @Column(name = "gb_sq") // 길드보스 순서
    private int sq;

    // 길드 보스 번호
    @Column(name = "gb_no")
    private int number;

    // 길드 보스 이름
    @Column(length = 100, name = "gb_name")
    private String name;

    // 길드 보스 이미지
    @Column(length = 300, name = "gb_image_boss")
    private String mainImg;

    // 길드 보스 속성 이미지
    @Column(length = 12, name = "gb_image_property")
    private String propertyImg;
}
