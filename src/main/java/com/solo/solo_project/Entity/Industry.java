package com.solo.solo_project.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Getter
@Setter
public class Industry {

    @Id
    int industry_Id;

    String industry_Name;
}
