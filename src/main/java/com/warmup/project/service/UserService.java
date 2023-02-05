package com.warmup.project.service;

import com.warmup.project.dto.UserResponseDTO;
import com.warmup.project.entity.User;
import com.warmup.project.dto.UserRequestDTO;
import com.warmup.project.util.CustomPage;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface UserService {
     UserResponseDTO createUser(UserRequestDTO body);
     List<UserResponseDTO> getUsers(Map<String, String> parameters, Pageable pageable);
     UserResponseDTO getUser(Long id);
     UserResponseDTO updateUser(Long id, UserRequestDTO body);
     UserResponseDTO patchUpdateUser(Long id, UserRequestDTO body);
     String deleteUser(Long id);

}
