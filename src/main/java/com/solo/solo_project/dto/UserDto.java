package com.solo.solo_project.dto;

import com.solo.solo_project.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;



@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    @AllArgsConstructor
    @NoArgsConstructor//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Getter
    public static class Post{
        @NotBlank
        @Email
        String email;

        String name;
        String sex;
        String company_name;
        String company_type;
        String company_location;
    }

    @AllArgsConstructor
    @Getter
    public static class single_response{
        long userId;
        String email;
        String name;
        String sex;
        String company_name;
        String company_type;
        String company_location;


    }


    @Getter
    @AllArgsConstructor
    public static class multi_response{
      private List<User> user;
      private  PageInfo pageInfo;

        public multi_response(java.util.List<User> user, Page page) {
            this.user = user;
            this.pageInfo = new PageInfo(page.getNumber()+1,
                    page.getSize(), page.getTotalElements(), page.getTotalPages());

        }
    }
}
