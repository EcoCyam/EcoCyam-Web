package upn.miage.ecocyam.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "evaluationId")
    private Integer evaluationId;
    @Column(name = "score")
    private Integer score;
    @Column(name = "comment")
    private String comment;
    @OneToOne
    @JoinColumn(name = "criteriaId", referencedColumnName = "criteriaId")
    private Criteria criteria;
    @OneToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private Item item;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;

    public Evaluation() {
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "evaluationId=" + evaluationId +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                ", criteria=" + criteria +
                ", item=" + item +
                ", user=" + user +
                '}';
    }
}
