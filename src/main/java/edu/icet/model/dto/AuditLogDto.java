package edu.icet.model.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuditLogDto {
    private Long id;

    private String action;

    private Long userId;

    private String details;

    private LocalDateTime timestamp;
}