package com.project.jpa;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "boss_element")
@Getter // Builder 이용하여 객체 생성
@Builder // AllArgsConstructor, NoArgsConstructor 같이 이용해야 컴파일 에러 발생 안함
@AllArgsConstructor // 모든 필드 파라미터로 받는 생성자 자동 생성
@NoArgsConstructor // 인자 없는 기본 생성자 자동 생성
@Setter
public class BossElementJPA {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
    @Column(name = "boss_element_id") // 길드보스 순서
    private int bossElementId;

    // 길드 보스 id : fk키, JPA에서는 객체 참조를 하기 때문에 객체 타입으로 받음(fk인 id키를 바로 사용 가능)
    @ManyToOne
    @JoinColumn(name = "boss_id")
    private GuardJPA boss;

    // 속성 id : fk키
    @ManyToOne
    @JoinColumn(name = "element_id")
    private ElementJPA element;

    // 길드 보스 속성 유무
    @Column(name = "boss_element_is_active", length = 2) // 길드보스 순서
    private String isActive; // O, X 로 나눔

}
