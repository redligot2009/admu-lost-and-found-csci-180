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

    public Location(String roomName, String buildingName) {
        this.roomName = roomName;
        this.buildingName = buildingName;
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
}
