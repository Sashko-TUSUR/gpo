package com.dreamteam.TestingSystemNew.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_topic;

    private  String name;
    private int count;


    @ManyToOne
    @JoinColumn(name="id_didacticUnit")
    private DidacticUnit didacticUnit;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId_topic() {
        return id_topic;
    }

    public void setId_topic(Long id_topic) {
        this.id_topic = id_topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DidacticUnit getDidacticUnit() {
        return didacticUnit;
    }

    public void setDidacticUnit(DidacticUnit didacticUnit) {
        this.didacticUnit = didacticUnit;
    }
}
