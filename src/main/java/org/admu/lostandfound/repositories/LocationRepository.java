package org.admu.lostandfound.repositories;

import org.admu.lostandfound.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    public Location findByBuildingNameAndRoomName(String buildingName, String roomName);
    public List<Location> findByBuildingName(String buildingName);

}
