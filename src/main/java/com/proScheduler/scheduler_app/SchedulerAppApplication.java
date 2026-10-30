package com.proScheduler.scheduler_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {
		"com.proScheduler.scheduler_app.repository",
		"com.proScheduler.scheduler_app.controller",
//		"com.proScheduler.scheduler_app.entity",
})
@SpringBootApplication
public class SchedulerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerAppApplication.class, args);
	}

}
