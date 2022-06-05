package com.dreamteam.TestingSystemNew.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Complexity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String complexity;

}
