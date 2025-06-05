package com.tsl.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserData {
    private String username;
    private String password;
}
