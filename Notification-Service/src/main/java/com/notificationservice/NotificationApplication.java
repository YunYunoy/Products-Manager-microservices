package com.notificationservice;

import com.notificationservice.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }


    @KafkaListener(topics = "orderNotification")
    public void handleNotification(OrderCreatedEvent orderCreatedEvent) {
        //TODO: maybe send email notification??
        log.info("notification for order {} ", orderCreatedEvent.getOrderNumber());
    }
}
