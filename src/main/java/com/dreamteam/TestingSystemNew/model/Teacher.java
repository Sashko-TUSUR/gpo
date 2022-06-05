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
public class Teacher {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @ManyToMany
    @JoinTable(name = "group_teacher", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Groups> groupsList;

    @ManyToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;



}
