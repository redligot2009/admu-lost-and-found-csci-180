package org.admu.lostandfound.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="lost_items")
public class LostItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name="title", nullable = false)
    private String title;

    @NotEmpty
    @Column(name="description", nullable = false)
    private String description;

    @NotNull
    @Column(name="date", nullable = false)
    private Date date;

    @NotNull
    @Column(name="time", nullable = false)
    private Time time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "lostItem")
    private Set<Claim> claims;

    public LostItem(String title, String description, Date date, Time time, Location location) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
