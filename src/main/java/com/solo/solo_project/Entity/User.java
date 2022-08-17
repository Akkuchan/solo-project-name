package com.solo.solo_project.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Comunity_user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long userID;

    @Column(length = 255, nullable = false)
    String email;

    @Column(length = 255, nullable = false)
    String name;

    @Column
    String sex;

    @Column(length = 255, nullable = false)
    String company_name;

    @Column
    String company_type;
    @Column
    String company_location;


}
