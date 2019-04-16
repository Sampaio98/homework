package com.webproject.homework.model;

import com.webproject.homework.service.exception.DataIntegrityValidationException;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "LANCHE")
public class Snack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    @ManyToOne
    private Session session;

    @ManyToMany
    @JoinTable(name="LANCHE_INGREDIENTE",
            joinColumns = @JoinColumn(name="lanche_fk"),
            inverseJoinColumns = @JoinColumn(name="ingrediente_fk"))
    private List<Ingredient> ingredients;

    public Snack() {
        this.ingredients = new ArrayList<>();
        this.price = new BigDecimal(0);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return Objects.equals(id, snack.id) &&
                Objects.equals(name, snack.name) &&
                Objects.equals(description, snack.description) &&
                Objects.equals(price, snack.price) &&
                Objects.equals(session, snack.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, session);
    }

    @Override
    public String toString() {
        return "Snack{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", session=" + session +
                '}';
    }

    public void update(Snack snack) {
        BeanUtils.copyProperties(snack,this,"id");
    }

    public void validateFields() {
        if (this.name.equals("") || this.description.equals("") || this.price == null) {
            throw new DataIntegrityValidationException("É necessário preencher todos os campos para cadastro.".toUpperCase());
        }
    }
}
