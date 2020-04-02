package upn.miage.ecocyam.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "itemId")
    private Integer itemId;
    @Column(name = "name")
    private String name;
    @Column(name = "overallscore")
    private Integer overallScore;
    @Column(name = "barcode")
    private String barcode;
    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId", nullable = false)
    private Category category;
    @ManyToMany(mappedBy = "items")
    private Set<User> users;

    public Item() {
    }

    public Item(Item item) {
        this.name = item.name;
        this.overallScore = item.overallScore;
        this.category = item.category;
        this.users = item.users;
        this.barcode = item.barcode;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", overallScore=" + overallScore +
                ", barcode='" + barcode + '\'' +
                ", category=" + category +
                ", users=" + users +
                '}';
    }
}
