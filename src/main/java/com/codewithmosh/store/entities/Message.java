package com.codewithmosh.store.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
// transfer data that we want
// others to publicly see
// not password or sensible data
public class Message {
    private Long id;
    private String name;
    private String email;
}
