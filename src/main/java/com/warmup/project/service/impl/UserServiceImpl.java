package com.warmup.project.service.impl;

import com.warmup.project.dto.UserResponseDTO;
import com.warmup.project.entity.User;
import com.warmup.project.repository.UserRepository;
import com.warmup.project.dto.UserRequestDTO;
import com.warmup.project.service.UserService;
import com.warmup.project.util.CustomPage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final URLQueryParamsService paramsService;

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserResponseDTO createUser(UserRequestDTO body){
        if(userRepository.existsUserByUsername(body.getUsername())) {
            throw new IllegalArgumentException("This username is taken. Please try another username.");}
        User user = modelMapper.map(body, User.class);
        user.setCreatedAt(new Date());
        user.setCreatedBy("Admin");
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }
    public List<UserResponseDTO> getUsers(Map<String, String> parameters, Pageable pageable){
        Set<String> parameterTypes = new HashSet<>(Arrays.asList(
                new String[]{"username", "firstName", "lastName", "age"}));
        return paramsService.executeQueryWithParams(parameters, parameterTypes, pageable, User.class).getResultList()
                .stream()
                .map(obj -> {User user = (User) obj; return modelMapper.map(user, UserResponseDTO.class);})
                .collect(Collectors.toList());
    }
    public UserResponseDTO getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() ? modelMapper.map(user.get(), UserResponseDTO.class) : null;
    }
    public UserResponseDTO updateUser(Long id, UserRequestDTO body){
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()){
            User user = optUser.get();
            modelMapper.map(body, user);
            user.setUpdatedAt(new Date());
            user.setUpdatedBy("Eren");
            return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
        }
        return null;
    }
    public UserResponseDTO patchUpdateUser(Long id, UserRequestDTO body){
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()){
            User user = optUser.get();
            if (body.getUsername()!=null)  user.setUsername(body.getUsername());
            if (body.getFirstName()!=null)  user.setFirstName(body.getFirstName());
            if (body.getLastName()!=null)  user.setLastName(body.getLastName());
            if (body.getNationalID()!=null)  user.setNationalID(body.getNationalID());
            if (body.getPassword()!=null)  user.setPassword(body.getPassword());
            user.setUpdatedAt(new Date());
            user.setUpdatedBy("Eren");
            return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
        }
        return null;
    }
    public String deleteUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.delete(user.get());
            return "User is successfully deleted.";
        }
        throw new IllegalArgumentException("User is not found with id of: " + id.toString());
    }

}
