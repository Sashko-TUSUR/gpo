package com.dreamteam.TestingSystemNew.model;

import javax.persistence.*;
import java.util.List;


@Entity


public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long name;

    @ManyToMany
    @JoinTable(name = "group_teacher", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teacherList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getName(String number) {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }
}