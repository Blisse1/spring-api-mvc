package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.RegisterUserRequest;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;


@RestController// to return data as json
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // @RequestMapping ("/users") // by default uses get method
    @GetMapping
    public Iterable<UserDto> getAllUsers(
            @RequestHeader(required = false, name = "x-auth-token") String authToken,
            @RequestParam(required = false, defaultValue = "") String sort
    ){
        System.out.println(authToken);
        if(!Set.of("name", "email").contains(sort))
            sort = "name";
        return userRepository.findAll(Sort.by(sort))
                .stream()
                // .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping ("/{id}")
//    public User getUser(@PathVariable Long id){
//        return userRepository.findById(id).orElse(null);
//    }

    // response entity to do status code
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);
        if(user == null){
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
        //var userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder){
        var user = userMapper.toEntity(request);
        userRepository.save(user);

        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }
}
