package com.codewithmosh.store.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserDto {
//    @JsonIgnore // to ignore certain fields from being converted into json
    @JsonProperty("user_id") // to rename properties
    private Long id;
    private String name;
    private String email;
//    @JsonInclude(JsonInclude.Include.NON_NULL) // not include properties with null values
//    private String phoneNumber;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime createdAt;
}
