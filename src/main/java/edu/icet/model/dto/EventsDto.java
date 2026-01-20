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

    private double basePrice;

    private  String highDemand;

    private String status;

}
