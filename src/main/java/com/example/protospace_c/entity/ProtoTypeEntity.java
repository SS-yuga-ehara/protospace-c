package com.example.protospace_c.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProtoTypeEntity {
    private Long id;
    private String name;
    private String image;
    private String catchcopy;
    private String concept;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String userName;
}
