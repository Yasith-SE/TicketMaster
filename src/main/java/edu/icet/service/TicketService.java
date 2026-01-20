package edu.icet.service;

import edu.icet.model.entity.*;
import edu.icet.repository.*;
import edu.icet.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final EventsRepository eventsRepository;

    @Transactional
    @AuditFailure
    public void holdSeat(Long seatId, Long userId) {

        SeatsEntity seat = seatRepository.findByIdWithLock(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));


        if (AppConstants.STATUS_HELD.equalsIgnoreCase(seat.getStatus())) {

            if (seat.getHoldExpiry().isAfter(LocalDateTime.now())) {
                long seconds = Duration.between(LocalDateTime.now(), seat.getHoldExpiry()).getSeconds();
                throw new RuntimeException("Seat locked. Try again in " + seconds + "s");
            }
        }

        if (AppConstants.STATUS_SOLD.equalsIgnoreCase(seat.getStatus())) {
            throw new RuntimeException("Seat is already sold");
        }

        seat.setStatus(AppConstants.STATUS_HELD);
        seat.setHeldByUserId(userId);
        seat.setHoldExpiry(LocalDateTime.now().plusMinutes(10));
        seatRepository.save(seat);
    }

    public Double calculatePrice(Long userId, Long eventId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        EventsEntity event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));


        Double price = event.getBasePrice();

        if (AppConstants.TIER_VIP.equalsIgnoreCase(user.getTier())) {
            if (!event.getIsHighDemand()) {
                price = price * 0.90;
            }
        }

        return price;
    }
}