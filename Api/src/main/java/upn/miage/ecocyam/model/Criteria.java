package upn.miage.ecocyam.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "criteria")
public class Criteria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "criteriaId")
    private Integer criteriaId;

    @Column(name = "name")
    private String name;

    public Criteria() {
    }

    public Criteria(Criteria criteria) {
        this.criteriaId = criteria.criteriaId;
        this.name = criteria.name;
    }

    public Integer getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(Integer criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "criteriaId=" + criteriaId +
                ", name='" + name +
                '}';
    }

}
