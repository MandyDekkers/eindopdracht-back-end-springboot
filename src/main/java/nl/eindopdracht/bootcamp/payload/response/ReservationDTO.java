package nl.eindopdracht.bootcamp.payload.response;

public class ReservationDTO {

    private String firstName;
    private String lastName;
    private long id;
    private String name;
    private String date;
    private String maxAmountMembers;
    private String niveau;
    private String comment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
