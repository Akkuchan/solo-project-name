package com.solo.solo_project.repository;

import com.solo.solo_project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByindustryId(int industry_Id);// 왜 에러 났는지 다시 확인해보기

    Optional<User> findByEmail(String email);
}
