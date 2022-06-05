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

}
