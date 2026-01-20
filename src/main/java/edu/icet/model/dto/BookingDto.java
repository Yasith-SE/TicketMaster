package edu.icet.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDto {
    private Long bookingId;

    private Long userId;

    private Long seatId;

    private String status;
}