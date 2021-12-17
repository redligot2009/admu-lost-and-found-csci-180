package org.admu.lostandfound.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name="title", nullable = false)
    private String title;

    @NotEmpty
    @Column(name = "room_name", nullable = false)
    private String roomName;

    @NotEmpty
    @Column(name = "building_name", nullable = false)
    private String buildingName;

    @Column(name = "description", nullable = true)
    private String description;
  
    public Location(String title, String roomName, String buildingName, String description) {
        this.title = title;
        this.roomName = roomName;
        this.buildingName = buildingName;
        this.description = description;
    }

    // no-arg constructor
    public Location() {
        this.title = null;
        this.roomName = null;
        this.buildingName = null;
        this.description = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", buildingName='" + buildingName + '\'' +
                '}';
    }
}
