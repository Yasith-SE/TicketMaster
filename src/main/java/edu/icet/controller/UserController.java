package edu.icet.controller;

import edu.icet.model.dto.UserDto;
import edu.icet.model.entity.UserEntity;
import edu.icet.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final TicketService ticketService;

    @PostMapping("/add")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(ticketService.createUser(userDto));
    }
}