package com.pandi.medapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedicineReminderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicineReminderApplication.class, args);
    }
}
