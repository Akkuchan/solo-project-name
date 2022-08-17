package com.solo.solo_project.controller;



import com.solo.solo_project.Entity.User;
import com.solo.solo_project.dto.UserDto;
import com.solo.solo_project.service.UserService;
import com.solo.solo_project.mapper.Usermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/buisinessman")
public class UserController {

    UserService userService;
    Usermapper mapper;


    public UserController(UserService userService, Usermapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody UserDto.Post requestBody){
        System.out.println(requestBody.getEmail());
        System.out.println("시작");
        User user = mapper.userPostDtoToUser(requestBody);
        System.out.println("!!!!!!!!!!!!!!!!!!");
        System.out.println(user.getUserID());
        System.out.println(user.getEmail());
        System.out.println(user.getSex());
        System.out.println(user.getCompany_name());
        System.out.println(user.getCompany_type());
        System.out.println(user.getCompany_location());
        System.out.println("매핑 통과");
        User createdUser = userService.createUser(user);
        System.out.println("서비스 통과");
        return new ResponseEntity<>(
            mapper.userToUserResponseDto(createdUser),
                HttpStatus.CREATED);


    }
//
//    /*user-id 기준으로 검색 */
//    @GetMapping("/{user-id}")
//    public  ResponseEntity getUser(@PathVariable("user-id") long userID){
//        User user = userService.findUserByUserID(userID);
//
//        return new ResponseEntity(mapper.userToUserResponseDto(user),
//                HttpStatus.OK);
//
//    }
//
//    /*전 회원 검색 */
//    @GetMapping()
//    public ResponseEntity getUsers(@Positive @RequestParam int page, @Positive @RequestParam int size){
//
//        Page<User> pageUsers = userService.findUsers(page-1,size);
//        List<User> users = pageUsers.getContent();
//
//        return new ResponseEntity<>(
//                mapper.userToUsersResponses(users, pageUsers),
//                HttpStatus.OK);
//    }
//
//    /*지역명을 기준으로 검색 */
//    @GetMapping("/{location}")
//    public  ResponseEntity getUser(@PathVariable("location") int location_id){
//        User user = userService.findUserByLocation(location_id);
//
//        return new ResponseEntity<>(
//                mapper.userToUsersResponses(users, pageUsers),
//                HttpStatus.OK);
//    }
//    }
//
//    /*업종을 기준으로 검색 */
//    @GetMapping("/{industry}")
//    public  ResponseEntity getUsers(@PathVariable("industry") int industry_id){
//        User user = userService.findUserByIndustry(industry_id);
//
//        return new ResponseEntity<>(
//                mapper.userToUsersResponses(users, pageUsers),
//                HttpStatus.OK);
//    }



}
