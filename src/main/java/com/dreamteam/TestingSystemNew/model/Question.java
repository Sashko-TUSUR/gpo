package com.dreamteam.TestingSystemNew.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id_question;

    private String questionJSON;


    @ManyToOne
    @JoinColumn(name="id_topic")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "Complexity")
    private Complexity complexity;

    public Question(String converterToJSON, Complexity complexity, Topic topic) {
    }


}
