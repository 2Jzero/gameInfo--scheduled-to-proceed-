package com.project.jpa;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "element")
@Getter // Builder 이용하여 객체 생성
@Builder // AllArgsConstructor, NoArgsConstructor 같이 이용해야 컴파일 에러 발생 안함
@AllArgsConstructor // 모든 필드 파라미터로 받는 생성자 자동 생성
@NoArgsConstructor // 인자 없는 기본 생성자 자동 생성
@Setter
public class ElementJPA {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
    @Column(name = "el_id") // 길드보스 순서
    private int elementId;

    // 속성 이름
    @Column(length = 20, name = "el_name")
    private String elementName;

    // 속성 이미지
    @Column(length = 300, name = "el_image")
    private String elementImg;
}
