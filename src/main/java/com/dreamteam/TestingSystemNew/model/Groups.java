package com.dreamteam.TestingSystemNew.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity


public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "group_teacher", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teacherList;


}