package edu.icet.service;

import edu.icet.model.dto.EventsDto;
import edu.icet.model.dto.SeatsDto;
import edu.icet.model.dto.UserDto;
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

    public UserEntity createUser(UserDto dto) {
        return userRepository.save(UserEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .tier(dto.getTier())
                .build());
    }

    // 2. Create Event
    public EventsEntity createEvent(EventsDto dto) {
        return eventsRepository.save(EventsEntity.builder()
                .name(dto.getName())
                .basePrice(dto.getBasePrice())
                .isHighDemand(dto.getHighDemand())
                .eventDate(LocalDateTime.parse(dto.getEventDate())) // Format: "2026-01-20T10:00:00"
                .build());
    }

    // 3. Create Seat
    public SeatsEntity createSeat(SeatsDto dto) {
        EventsEntity event = eventsRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return seatRepository.save(SeatsEntity.builder()
                .seatNumber(dto.getSeatNumber())
                .event(event)
                .status("AVAILABLE") // Default status
                .build());
    }
}