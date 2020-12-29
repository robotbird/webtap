package com.webtap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebtapApplication {
	
//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(WebtapApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(WebtapApplication.class, args);
    }

}