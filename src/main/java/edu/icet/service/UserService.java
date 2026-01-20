package edu.icet.service;

import edu.icet.model.dto.UserDto;
import edu.icet.model.entity.UserEntity;
import edu.icet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void save(UserDto userDto){


    }

}
