package com.project.service;

import com.project.jpa.GuardJPA;
import com.project.jpa.GuardLibJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Guard;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    private final GuardLibJpa guardLibJpa;

    @Autowired
    public MainService(GuardLibJpa guardLibJpa) {
        this.guardLibJpa = guardLibJpa;
    }

    public List<GuardJPA> mainPage() {
        List<GuardJPA> bossList = new ArrayList<>();

        bossList = guardLibJpa.mainPage();

        return bossList;
    }
}
