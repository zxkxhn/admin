package com.zxk.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.zxk.*","com.ss.*"})
public class Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("启动成功!!");
	}

}
