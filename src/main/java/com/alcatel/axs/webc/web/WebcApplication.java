// Copyright (c) 2014 KMS Technology, Inc.
package com.alcatel.axs.webc.web;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan("com.alcatel.axs.webc")
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class WebcApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebcApplication.class);
    }
    
    public static void main(String... args) throws IOException {
    	final ApplicationContext applicationContext = SpringApplication.run(WebcApplication.class, args);

//        ContactService contactService = appContext.getBean(ContactService.class);
//        String filePath = (args.length > 0)? args[0] : "etc/contacts.txt";
//        contactService.loadContacts(filePath);
    }
}