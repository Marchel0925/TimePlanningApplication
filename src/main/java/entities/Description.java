package entities;

import javax.persistence.*;

@Entity
@Table(name = "descriptionTable")
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "eventDescription")
    private String eventDescription;

    @ManyToOne
    @JoinColumn(name = "eventId")
    private Event event;

    public Description() {
    }

    public Description(String eventDescription, Event event) {
        this.eventDescription = eventDescription;
        this.event = event;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
