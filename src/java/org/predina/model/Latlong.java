/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.predina.model;

/**
 *
 * @author akinw
 */
public class Latlong {
    private double latitude;
    private double longitude;
    private int riskRating;
    private String riskColour;
    
    public Latlong() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getRiskRating() {
        return riskRating;
    }

    public void setRiskRating(int riskRating) {
        this.riskRating = riskRating;
    }

    public String getRiskColour() {
        return riskColour;
    }

    public void setRiskColour(String riskColour) {
        this.riskColour = riskColour;
    }
}
