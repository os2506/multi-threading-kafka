package com.oss.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class OrderCleanupService {

    @Scheduled(cron = "0 0 3 * * ?") // Tous les jours à 3h du matin
    public void cleanProcessedOrders() {
        System.out.println("Cleaning processed orders at: " + LocalDateTime.now());
        // Simuler suppression en base de données
    }
}

