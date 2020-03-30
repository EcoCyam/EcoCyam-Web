package upn.miage.ecocyam.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categoryId")
    private Integer categoryId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Item> item;

    public Category(Category category) {
        this.categoryId = category.categoryId;
        this.name = category.name;
        this.item = category.item;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Item> getItem() {
        return item;
    }

    public void setItem(Set<Item> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", item=" + item +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return getCategoryId().equals(category.getCategoryId()) &&
                getName().equals(category.getName()) &&
                getItem().equals(category.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryId(), getName(), getItem());
    }
}
