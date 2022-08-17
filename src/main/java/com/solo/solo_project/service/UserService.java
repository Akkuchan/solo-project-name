package com.solo.solo_project.service;

import com.solo.solo_project.Entity.User;
import com.solo.solo_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        long userID = user.getUserID();
        verifyExistUser(user.getEmail());
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(user.getUserID());
        System.out.println(user.getEmail());
        System.out.println(user.getSex());
        System.out.println(user.getCompany_name());
        System.out.println(user.getCompany_type());
        System.out.println(user.getCompany_location());
        return userRepository.save(user);
    }
//
//    public User findUserByUserID(long userID) {
//
//
//        return null;
//    }
//
//
//    public Page<User> findUsers(int i, int size) {
//
//
//        return null;
//    }

    public User findUserByLocation(int company_location) {
        return null;
    }

    public User findUserByIndustry(long userID) {
        return null;
    }





    private void verifyExistUser(String email) {
        Optional<User> member =userRepository.findByEmail(email);
        if (member.isPresent())
            throw new RuntimeException();////추후 수정
    }


}
