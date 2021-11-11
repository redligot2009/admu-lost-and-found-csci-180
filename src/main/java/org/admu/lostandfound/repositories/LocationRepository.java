package org.admu.lostandfound.repositories;

import org.admu.lostandfound.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    public Location findByBuildingAndRoom(String buildingName, String roomName);
    public Location[] findByBuilding(String buildingName);

}
