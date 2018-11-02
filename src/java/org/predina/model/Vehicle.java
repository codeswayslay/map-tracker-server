/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.predina.model;

import java.util.List;

/**
 *
 * @author akinw
 */
public class Vehicle {
    private String vehicle;
    private List<RealTimeLocation> locations;

    public Vehicle() {
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public List<RealTimeLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<RealTimeLocation> locations) {
        this.locations = locations;
    }
}
