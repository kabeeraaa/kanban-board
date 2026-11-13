package com.bej.kanbanService.config;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

public class BoardsNotifications {
    @Id
    private String boardId;
    private String boardMessage;
    private boolean boardMessageStatus = false;

}
