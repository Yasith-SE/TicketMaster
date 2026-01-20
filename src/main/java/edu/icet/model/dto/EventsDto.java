package edu.icet.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventsDto {
    private Long eventId;


    private String name;

    private Double basePrice;

    private Boolean highDemand;

    private String eventDate;
}