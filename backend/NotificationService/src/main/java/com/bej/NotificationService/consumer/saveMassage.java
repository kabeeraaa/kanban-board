package com.bej.NotificationService.consumer;

import com.bej.NotificationService.Service.INotificationService;
import com.bej.NotificationService.domain.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class saveMassage {

    @Autowired
    INotificationService iNotificationService;

    @Autowired
    public saveMassage(INotificationService iNotificationService) {
        this.iNotificationService = iNotificationService;
    }


    @RabbitListener(queues = "user-notification-queue")
    public void saveNotification(Notification notification) {

        System.out.println(notification);
//
//        System.out.println(jsonObject);
//        String userId=jsonObject.getJsonObject().get("userId").toString();
//        if(iNotificationService.findById(userId).isEmpty()){
//            Notification notification = new Notification();
//            Map<String,Boolean> messages = new HashMap();
//            messages.put(jsonObject.getJsonObject().get("Notification").toString(),false);
//            notification.setMessage(messages);
//            notification.setJsonObject(jsonObject.getJsonObject());
//            notification.setUserId(userId);
//            INotificationRepository.save(notification);
//        }
//        Notification notification= INotificationRepository.findById(userId).get();
//        Map<String,Boolean> messages = notification.getMessage();
//        messages.put(jsonObject.getJsonObject().get("Notification").toString(),false);
//        notification.setMessage(messages);
//        INotificationRepository.save(notification);
////

    }
}
