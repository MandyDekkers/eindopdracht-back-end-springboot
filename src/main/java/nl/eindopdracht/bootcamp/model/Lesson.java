package nl.eindopdracht.bootcamp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private String date;

    @Column(name = "amountMembers")
    private String maxAmountMembers;

    @Column(name = "niveau")
    private String niveau;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Reservation> reservations;

    public Lesson() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxAmountMembers() {
        return maxAmountMembers;
    }

    public void setMaxAmountMembers(String maxAmountMembers) {
        this.maxAmountMembers = maxAmountMembers;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}


