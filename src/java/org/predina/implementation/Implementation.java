/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.predina.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.predina.implementation.util.CoordinatesLoader;
import org.predina.implementation.util.VehicleLocationLoader;
import org.predina.model.Latlong;
import org.predina.model.Vehicle;

/**
 *
 * @author akinw
 */
public class Implementation {
    private final CoordinatesLoader coordinates = CoordinatesLoader.getInstance();
    private final VehicleLocationLoader locations = VehicleLocationLoader.getInstance();
    
    public List<Vehicle> getVehicleLocations() {
        return locations.getVehicles();
    }
    
    public List<Latlong> getCordinates() {
        List<Latlong> points = coordinates.getCoordinatePoints();
        points.stream().forEach(coordinate -> {
            int riskRating = getRandomRiskRating();
            String ratingColour = getAllRiskRatingColourCodes().get(riskRating);
            
            coordinate.setRiskRating(riskRating);
            coordinate.setRiskColour(ratingColour);
        });
        
        return points;
    }
    
    private int getRandomRiskRating() {
        int min = 1;
        int max = 10;
        
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    private Map<Integer, String> getAllRiskRatingColourCodes() {
        Map<Integer, String> allColours = new HashMap<>();
        allColours.put(1, "#008000");
        allColours.put(2, "#008000");
        allColours.put(3, "#008000");
        allColours.put(4, "#FFFF00");
        allColours.put(5, "#FFFF00");
        allColours.put(6, "#FFA500");
        allColours.put(7, "#FFA500");
        allColours.put(8, "#FF0000");
        allColours.put(9, "#8B0000");
        allColours.put(10, "#8B0000");
        
        return allColours;
    }
}
