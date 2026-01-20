package edu.icet.model.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class UserDto {

    @Id
    private Long userId;

    private String name;

    private String tire;

    private String email;



}
