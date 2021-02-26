package nl.eindopdracht.bootcamp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReservationKey implements Serializable {

    @Column(name = "appuser_id")
    private Long appUserId;

    @Column(name = "lesson_id")
    private Long lessonId;

    public ReservationKey() {}
    public ReservationKey(long appUserId, long lessonId) {
        this.appUserId = appUserId;
        this.lessonId = lessonId;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationKey that = (ReservationKey) o;
        return appUserId.equals(that.appUserId) &&
                lessonId.equals(that.lessonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUserId, lessonId);
    }

}
