package entities;

import javax.persistence.*;

@Entity
@Table(name = "detailsTable")
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "timeId")
    private Time time;

    public Details() {
    }

    public Details(String location, String description, Time time) {
        this.location = location;
        this.description = description;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
