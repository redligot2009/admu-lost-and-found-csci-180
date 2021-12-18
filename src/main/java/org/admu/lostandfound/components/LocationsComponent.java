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
	
	public List<Location> getLocation(String buildingName, String roomName) throws RuntimeException
	{
		// returns a list of all locations
		if(buildingName==null && roomName==null){
			List<Location> foundLocations = locationRepo.findAll();
			return foundLocations;
		}

		// returns a list containing a single location
		List<Location> foundLocations = new ArrayList<>();
		Location foundLocation = locationRepo.findByBuildingNameAndRoomName(buildingName,roomName);
		foundLocations.add(foundLocation);

		if(foundLocation == null){
			throw new RuntimeException("No location found");
		}

		return foundLocations;


	}
	
	public Location postLocation(String title, String buildingName, String roomName, String description) throws RuntimeException
	{
		try{
			Location newLocation = new Location();
			newLocation.setTitle(title);
			newLocation.setBuildingName(buildingName);
			newLocation.setRoomName(roomName);
			newLocation.setDescription(description);

			newLocation = locationRepo.save(newLocation);
			return newLocation;
		} catch (Exception e){
			throw new RuntimeException("Bad Request");
		}

	}
	
	public Location putLocation(Long id, Location locationReq) throws RuntimeException
	{

		Optional<Location> savedLocation = locationRepo.findById(id);

		if(savedLocation.isPresent()){
			Location updateLocation = savedLocation.get();
			updateLocation.setBuildingName(locationReq.getBuildingName());
			updateLocation.setRoomName(locationReq.getRoomName());
			updateLocation.setTitle(locationReq.getTitle());
			updateLocation.setDescription(locationReq.getDescription());
			updateLocation = locationRepo.save(updateLocation);
			return updateLocation;
		}

		throw new RuntimeException("Location not found");
	}
	
	public Location deleteLocation(Long id) throws RuntimeException
	{
		Optional<Location> deleteLocation = locationRepo.findById(id);

		if(deleteLocation.isPresent()) {
			locationRepo.deleteById(id);
			return deleteLocation.get();
		}

		throw new RuntimeException("Location not found. No location deleted.");
	}
	
}
