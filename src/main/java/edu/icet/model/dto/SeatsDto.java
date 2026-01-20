package edu.icet.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SeatsDto {

    private Long seatsId;

    private String eventId;

    private String seatNumber;

    private String status;


}
