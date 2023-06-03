package com.notificationservice.service;

import com.notificationservice.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {


    @KafkaListener(topics = "orderNotification")
    public void handleNotification(OrderCreatedEvent orderCreatedEvent) {
        //TODO: maybe send email notification??
        log.info("notification for order {} ", orderCreatedEvent.getOrderNumber());
    }
}
