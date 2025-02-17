package com.oss.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync  // Active le support des tâches asynchrones
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);  // Nombre minimum de threads
        executor.setMaxPoolSize(10);  // Nombre max de threads
        executor.setQueueCapacity(20); // File d'attente des tâches
        executor.setThreadNamePrefix("AsyncTask-");
        executor.initialize();
        return executor;
    }
}

