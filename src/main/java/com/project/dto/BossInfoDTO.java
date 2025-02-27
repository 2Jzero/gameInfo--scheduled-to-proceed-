package com.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class BossInfoDTO {
    private String bossName;
    private String bossImage;
    private String elementNames; // "빛, 불, 물"
    private String elementImages; // "light.jpg, fire.jpg, water.jpg"


    private List<String> elementList;
    private List<String> elementImageList;

    public BossInfoDTO(String bossName, String bossImage, String elementNames, String elementImages) {
        this.bossName = bossName;
        this.bossImage = bossImage;
        this.elementNames = elementNames;
        this.elementImages = elementImages;
    }

    public void processElements() {
        this.elementList = Arrays.asList(this.elementNames.split(", "));
        this.elementImageList = Arrays.asList(this.elementImages.split(", "));
    }
}
