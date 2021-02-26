package nl.eindopdracht.bootcamp.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


@Entity
@Table(name = "reservation")
public class Reservation {

    @EmbeddedId
    private ReservationKey id;

    @ManyToOne
    @MapsId("appUserId")
    @JoinColumn(name = "appuser_id")
    private AppUser appUser;

    @ManyToOne
    @MapsId("lessonId")
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column
    private String comment;

    public ReservationKey getId() {
        return id;
    }

    public void setId(ReservationKey id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

