package nl.eindopdracht.bootcamp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    @Column(name = "kindoflesson")
    private String lessonName;

    @Column(name = "location")
    private String location;

    @Column(name = "date")
    private String date;

    @Column(name = "maxamountofmembers")
    private int amountMembers;

    //één relatie met AppUser:
    @ManyToMany (mappedBy = "lessons")
    private Set<AppUser> appUsers;

    //getters en setters:
    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmountMembers() {
        return amountMembers;
    }

    public void setAmountMembers(int amountMembers) {
        this.amountMembers = amountMembers;
    }

    public Set<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }
}
