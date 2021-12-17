package org.admu.lostandfound.components;

import org.admu.lostandfound.models.Location;
import org.admu.lostandfound.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LocationsComponent 
{

	// Repository @Autowire here
	@Autowired
	LocationRepository locationRepo;
	
	public List<Location> getLocation(String buildingName, String roomName)
	{

		if(buildingName==null && roomName==null){
			List<Location> foundLocations = locationRepo.findAll();
			return foundLocations;
		}
		List<Location> foundLocations = new ArrayList<>();
		Location foundLocation = locationRepo.findByBuildingNameAndRoomName(buildingName,roomName);
		foundLocations.add(foundLocation);
		return foundLocations;

	}
	
	public Location postLocation(String title, String buildingName, String roomName, String description)
	{
		Location newLocation = new Location();
		newLocation.setTitle(title);
		newLocation.setBuildingName(buildingName);
		newLocation.setRoomName(roomName);
		newLocation.setDescription(description);

		newLocation = locationRepo.save(newLocation);
		return newLocation;
	}
	
	public Location putLocation(Long id, String buildingName, String roomName)
	{

		Optional<Location> savedLocation = locationRepo.findById(id);

		if(savedLocation.isPresent()){
			Location updateLocation = savedLocation.get();
			updateLocation.setBuildingName(buildingName);
			updateLocation.setRoomName(roomName);
			updateLocation = locationRepo.save(updateLocation);
			return updateLocation;
		}

		return savedLocation.orElse(null);
	}
	
	public Location deleteLocation(Long id)
	{
		Optional<Location> deleteLocation = locationRepo.findById(id);

		if(deleteLocation.isPresent()) {
			locationRepo.deleteById(id);
			return deleteLocation.get();
		}
		return null;
	}
	
}
