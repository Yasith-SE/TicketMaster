package edu.icet.controller;

import edu.icet.model.dto.EventsDto;
import edu.icet.model.entity.EventsEntity;
import edu.icet.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final TicketService ticketService;

    @PostMapping("/add")
    public ResponseEntity<EventsEntity> createEvent(@RequestBody EventsDto eventsDto) {
        return ResponseEntity.ok(ticketService.createEvent(eventsDto));
    }
}