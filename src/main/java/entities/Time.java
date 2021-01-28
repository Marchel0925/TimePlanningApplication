package entities;

import javax.persistence.*;

@Entity
@Table(name = "timeTable")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "endDate")
    private java.sql.Date endDate;

    @Column(name = "startTime")
    private java.sql.Time startTime;

    @Column(name = "endTime")
    private java.sql.Time endTime;

    @ManyToOne
    @JoinColumn(name = "dateId")
    private Date date;

    public Time() {
    }

    public Time(java.sql.Date endDate, java.sql.Time startTime, java.sql.Time endTime, Date date) {
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    public java.sql.Time getStartTime() {
        return startTime;
    }

    public void setStartTime(java.sql.Time startTime) {
        this.startTime = startTime;
    }

    public java.sql.Time getEndTime() {
        return endTime;
    }

    public void setEndTime(java.sql.Time endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
