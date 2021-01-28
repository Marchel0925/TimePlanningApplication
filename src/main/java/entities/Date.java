package entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "dateTable")
public class Date {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private java.sql.Date date;

    @OneToMany(mappedBy = "date", fetch = FetchType.EAGER)
    private Set<Time> timeSet = new HashSet<>();

    public Date() {
    }

    public Date(java.sql.Date date, Set<Time> timeSet) {
        this.date = date;
        this.timeSet = timeSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public Set<Time> getTimeSet() {
        return timeSet;
    }

    public void setTimeSet(Set<Time> timeSet) {
        this.timeSet = timeSet;
    }
}
