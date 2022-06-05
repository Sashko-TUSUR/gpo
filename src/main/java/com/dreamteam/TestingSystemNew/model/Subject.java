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
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subjectId;

     private String name;

    @OneToMany(mappedBy="subject")
    private List<DidacticUnit> didacticUnit;


}
