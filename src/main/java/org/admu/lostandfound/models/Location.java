package org.admu.lostandfound.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="locations")
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name="room_name", nullable = false)
    private String roomName;

    @NotEmpty
    @Column(name="building_name", nullable = false)
    private String buildingName;

}
