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


    public Long getId_question() {
        return id_question;
    }

    public void setId_question(Long id_question) {
        this.id_question = id_question;
    }

    public String getQuestionJSON() {
        return questionJSON;
    }

    public void setQuestionJSON(String questionJSON) {
        this.questionJSON = questionJSON;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Complexity getComplexity() {
        return complexity;
    }

    public void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }
}
