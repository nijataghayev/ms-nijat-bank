package com.example.msnijatbank.service;

import com.example.msnijatbank.dao.entity.UserEntity;
import com.example.msnijatbank.dao.repository.UserRepository;
import com.example.msnijatbank.exceptions.NotFoundException;
import com.example.msnijatbank.exceptions.ValidationException;
import com.example.msnijatbank.mapper.UserMapper;
import com.example.msnijatbank.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: nijataghayev
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        log.info("ActionLog.getAllUsers.start");
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDto> userDtoList = userEntityList.stream()
                .map(userMapper::mapToDto)
                .toList();
        log.info("ActionLog.getAllUsers.end");
        return userDtoList;
    }

    public UserDto getUser(Long userId) {
        log.info("ActionLog.getUser.start userId {}", userId);
        var userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(
                        "USER_NOT_FOUND",
                        String.format("ActionLog.getUser.id %d not found", userId)));
        var userDto = userMapper.mapToDto(userEntity);
        log.info("ActionLog.getUser.end userId {}", userId);
        return userDto;
    }

    public void createUser(UserDto userDto) {
        log.debug("ActionLog.createUser.start user {}", userDto);
        if (userDto.getFinCode().length() != 7) {
            throw new ValidationException("FIN_CODE_LENGTH_MUST_BE_EQUALS_7",
                    String.format("ActionLog.createUser.error user fin code must be equal to 7, this now is " + userDto.getFinCode()));
        }
        UserEntity userEntity = userMapper.mapToEntity(userDto);
        userRepository.save(userEntity);
        log.debug("ActionLog.createUser.end user {}", userDto);
    }

    public void deleteUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                        "USER_NOT_FOUND",
                        String.format("ActionLog.deleteUser.id %d not found", userId)));
        userRepository.delete(userEntity);
    }
}