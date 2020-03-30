package upn.miage.ecocyam.model;

import javax.persistence.*;

@Entity
@Table(name = "criteria")
public class Criteria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "criteriaId")
    private Integer criteriaId;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "criteria")
    private Evaluation evaluation;

    public Criteria() {
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

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "criteriaId=" + criteriaId +
                ", name='" + name + '\'' +
                ", evaluation=" + evaluation +
                '}';
    }
}
