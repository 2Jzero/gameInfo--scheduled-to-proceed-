package com.project.guardLib;

import org.springframework.boot.SpringApplication;

public class TestGuardLibApplication {

	public static void main(String[] args) {
		SpringApplication.from(GuardLibApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
