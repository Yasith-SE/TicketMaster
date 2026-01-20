package edu.icet.controller;

import edu.icet.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {

    private final TicketService ticketService;

    @PostMapping("/{id}/hold")
    public ResponseEntity<String> holdSeat(@PathVariable Long id, @RequestParam Long userId) {
        ticketService.holdSeat(id, userId);
        return ResponseEntity.ok("Seat held successfully for 10 minutes.");
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<Double> getPrice(@PathVariable Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(ticketService.calculatePrice(userId, id));
    }
}