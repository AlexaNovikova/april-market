package ru.geekbrains.april.market.dtos;

import lombok.Data;
import ru.geekbrains.april.market.models.User;

@Data
public class UserDto {
    private String username;
    private String email;


    public UserDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
