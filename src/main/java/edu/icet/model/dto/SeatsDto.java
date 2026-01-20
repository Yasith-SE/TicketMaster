package edu.icet.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeatsDto {
    private Long seatsId;

    private Long eventId; // ID should be Long, not String

    private String seatNumber;

    private String status;
}