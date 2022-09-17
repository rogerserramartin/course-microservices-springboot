package com.rogerserra.notification.service;

import com.rogerserra.clients.notification.NotificationRequest;
import com.rogerserra.notification.entity.Notification;
import com.rogerserra.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest){
        Notification notification = Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCUstomerEmail(notificationRequest.toCustomerEmail())
                .sender("Roger Serra")
                .message(notificationRequest.message())
                .sentAt(LocalDateTime.now())
                .build();
        notificationRepository.save(notification); // podriamos enviar por twilio a continuacion
    }



}
