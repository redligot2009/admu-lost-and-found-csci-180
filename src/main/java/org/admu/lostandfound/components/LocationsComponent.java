package org.admu.lostandfound.components;

import org.admu.lostandfound.models.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationsComponent 
{

	// Repository @Autowire here
	
	
	public Location getLocation(String title, String buildingName, String roomName)
	{
		return null;
	}
	
	public Location postLocation(String title, String buildingName, String roomName)
	{
		return null;
	}
	
	public Location putLocation(Integer id, String buildingName, String roomName)
	{
		return null;
	}
	
	public Location deleteLocation(Integer id)
	{
		return null;
	}
	
}
