package upn.miage.ecocyam.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categoryId")
    private Integer categoryId;

    @Column(name = "name")
    private String name;

    public Category() {}

    public Category(Category category) {
        this.categoryId = category.categoryId;
        this.name = category.name;
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

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return getCategoryId().equals(category.getCategoryId()) &&
                getName().equals(category.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryId(), getName());
    }
}
