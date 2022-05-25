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

    public List<DidacticUnit> getDidacticUnit() {
        return didacticUnit;
    }

    public void setDidacticUnit(List<DidacticUnit> didacticUnit) {
        this.didacticUnit = didacticUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
