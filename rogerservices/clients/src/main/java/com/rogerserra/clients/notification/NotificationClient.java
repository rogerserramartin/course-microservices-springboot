package com.rogerserra.clients.notification;

import com.rogerserra.clients.fraud.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "notification")
public interface NotificationClient {
    @GetMapping(path = "api/v1/notification")
    void sendNotification(NotificationRequest notificationRequest);
}


