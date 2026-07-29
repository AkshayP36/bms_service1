package com.lld.BookMyShow.dtos;

import com.lld.BookMyShow.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponseDto {

    private User user;
}
