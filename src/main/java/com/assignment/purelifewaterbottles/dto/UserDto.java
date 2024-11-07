package com.assignment.purelifewaterbottles.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private String userId;
    private String username;
    private String password;
}
