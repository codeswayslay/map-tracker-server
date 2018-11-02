/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.predina.implementation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.predina.model.RealTimeLocation;
import org.predina.model.Vehicle;

/**
 *
 * @author akinw
 */
public class VehicleLocationLoader {
    private static VehicleLocationLoader INSTANCE;
    private final List<Vehicle> vehicles = new ArrayList<>();
    
    public static VehicleLocationLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VehicleLocationLoader();
        }
        return INSTANCE;
    }
    
    private VehicleLocationLoader() {
        loadVehicleLocations();
    }
    
    private void loadVehicleLocations() {
        try {
            String[] preferredVehicles = {"Vehicle_1290 Hiab",
                 "Vehicle_430",
                 "Vehicle_1802",
                 "Vehicle_432",
                 "Vehicle_433",
                 "Vehicle_434",
                 "Vehicle_436"};
            
            List<String> preferredVehicles_ = Arrays.asList(preferredVehicles);
            
            String location_file = "realtimelocation.csv";
            InputStream instream = VehicleLocationLoader.class.getClassLoader().getResourceAsStream(location_file);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
            
            Map<String, Vehicle> tempStore = new HashMap<>();
            
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                Vehicle vehicle = new Vehicle();
                
                RealTimeLocation location = new RealTimeLocation();
                List<RealTimeLocation> locations = new ArrayList<>();
                
                if (index != 0) {//first line is the column. We don't need that
                    String[] values = line.split(",");
                    if (values.length == 4) {
                        vehicle.setVehicle(values[1]);
                        
                        location.setTime(values[0]);
                        location.setLatitude(Double.valueOf(values[2]));
                        location.setLogitude(Double.valueOf(values[3]));
                        locations.add(location);
                        
                        if (!preferredVehicles_.contains(vehicle.getVehicle())) {
                            continue;
                        }
                        
                        vehicle.setLocations(locations);
                        
                        if (tempStore.size() < 6) {
                            if (!tempStore.containsKey(vehicle.getVehicle())) {//vehicle hasn't been added to map
                                tempStore.put(vehicle.getVehicle(), vehicle);
                            } else {
                                //if vehicle is already in map, simply get the vehicle out and alter the location list,
                                //add the list back, and put it back in the map
                                alterLocationListInMap(tempStore, vehicle, location);
                            }
                        } else {
                            //at this point, we are no longer adding any vehicles. We are only concerned with
                            //updating the location list of already added vehicles
                            if (tempStore.containsKey(vehicle.getVehicle())) {
                                alterLocationListInMap(tempStore, vehicle, location);
                            }
                        }
                        
                    }
                }
                System.out.println("processed record at index " + index);
                index += 1;
                
                if (index == 40001) {
                    break;//we only need to read 40,000 lines
                }
            }
            
            tempStore.entrySet().stream().map((pair) -> {
                String key = (String) pair.getKey();
                return pair;
            }).map((pair) -> (Vehicle) pair.getValue()).forEachOrdered((v) -> {
                vehicles.add(v);
            });
            
            //for test purposes. Ignore
            /*System.out.println("total vehicles processed: " + tempStore.size());
            tempStore.entrySet().stream().map((pair) -> {
                String key = (String) pair.getKey();
                Vehicle v = (Vehicle) pair.getValue();
                System.out.println("processed for vehicle: " + key);
                System.out.println("vehicle has " + v.getLocations().size() + " locations");
                return v;
            }).map((v) -> {
                System.out.println("The location coordinates are:");
                return v;
            }).forEachOrdered((v) -> {
                for (RealTimeLocation r : v.getLocations()) {
                    System.out.println("time: " + r.getTime() + ", latitude: " + r.getLatitude() + ", longitude: " + r.getLogitude());
                }
                System.out.println();
                System.out.println();
            });*/
        } catch (IOException ex) {
            //Again, Should probably log this, but trying to conserve space, as this is a free hosting.
            //As long as all files are in place, everything should be fine.
            Logger.getLogger(VehicleLocationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    private void alterLocationListInMap(Map<String, Vehicle> tempStore, Vehicle vehicle, RealTimeLocation location) {
        Vehicle storedVehicle = tempStore.get(vehicle.getVehicle());
        List<RealTimeLocation> listToAlter = storedVehicle.getLocations();
        listToAlter.add(location);
        
        storedVehicle.setLocations(listToAlter);
        tempStore.put(vehicle.getVehicle(), storedVehicle);
    }
}
