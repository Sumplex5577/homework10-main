package com.example.homeworkten.dtos;

import com.example.homeworkten.models.Cart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonDto {
    private Long idPerson;
    private String firstName;
    private String lastName;
    private String email;
    private List<Cart> carts;
    private String username;
    private String password;
    private String passwordConfirm;
}
