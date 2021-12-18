package org.admu.lostandfound.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
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

    @NotEmpty
    @Column(name="item_status", nullable = false)
    private String itemStatus;

    @Column(name="date", nullable = false)
    private LocalDate date;

    @Column(name="time", nullable = false)
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "lostItem", fetch=FetchType.EAGER)
    private Set<Claim> claims;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public LostItem() {}

    public LostItem(
            String title,
            String description,
            String itemStatus,
            LocalDate date,
            LocalTime time,
            Location location,
            Category category
    ) {
        this.title = title;
        this.description = description;
        this.itemStatus = itemStatus;
        this.date = date;
        this.time = time;
        this.location = location;
        this.category = category;
        this.claims = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Set<Claim> getClaims() {
        return claims;
    }

    public void setClaims(Set<Claim> claims) {
        this.claims = claims;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getItemStatus() { return itemStatus; }

    public void setItemStatus(String itemStatus) { this.itemStatus = itemStatus; }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }
}
