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
}
