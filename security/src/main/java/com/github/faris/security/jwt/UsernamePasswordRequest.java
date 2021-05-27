package com.github.faris.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsernamePasswordRequest implements Serializable {

    private String username;

    private String password;
}
