package com.solo.solo_project.mapper;

import com.solo.solo_project.Entity.User;
import com.solo.solo_project.dto.UserDto;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Usermapper {


    public User userPostDtoToUser(UserDto.Post userPostDto) {
        System.out.println("11111111111111111111111111111" +userPostDto.getEmail() );
        return new User(0L,
                userPostDto.getEmail(),
                userPostDto.getName(),
                userPostDto.getSex(),
                userPostDto.getCompany_name(),
                userPostDto.getCompany_type(),
                userPostDto.getCompany_location());
    }

    public UserDto.single_response userToUserResponseDto(User user) {
       return new UserDto.single_response(user.getUserID(), user.getEmail(),
                user.getName(), user.getSex(), user.getCompany_name(),
                 user.getCompany_type(), user.getCompany_location());

    }

    public UserDto.multi_response userToUsersResponses(List<User> users, Page page) {
        return new UserDto.multi_response(users, page);
    }
}
