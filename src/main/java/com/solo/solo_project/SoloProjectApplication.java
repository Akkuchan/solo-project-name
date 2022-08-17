package com.solo.solo_project;

import jdk.jfr.Enabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootApplication
public class SoloProjectApplication {

	public static void main(String[] args) {
		System.out.println("111111");
		SpringApplication.run(SoloProjectApplication.class, args);
		System.out.println("222222");
	}

}
