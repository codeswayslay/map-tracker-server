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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.predina.model.Latlong;

/**
 *
 * @author akinw
 */
public class CoordinatesLoader {
    private static CoordinatesLoader INSTANCE;
    private final List<Latlong> coordinatePoints = new ArrayList<>();
    
    public static CoordinatesLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CoordinatesLoader();
        }
        return INSTANCE;
    }
    
    private CoordinatesLoader() {
        loadCoordinatePoints();
    }
    
    private void loadCoordinatePoints() {
        try {
            String csv_file = "coordinates.csv";
            InputStream instream = CoordinatesLoader.class.getClassLoader().getResourceAsStream(csv_file);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
            
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                Latlong latlong = new Latlong();
                if (index != 0) {//first line is the column. We don't need that
                    String[] values = line.split(",");
                    if (values.length == 2) {
                        latlong.setLatitude(Double.valueOf(values[0]));//latitude on the left, according to file
                        latlong.setLongitude(Double.valueOf(values[1]));//logitude on the right, according to file
                        
                        //String code = "point"+index;
                        
                        coordinatePoints.add(latlong);
                        System.out.println("read at " + index + "for latitude " + values[0] + " and logitude " + values[1]);
                    }
                }
                index += 1;
            }
        } catch (IOException ex) {
            //Should probably log this, but trying to conserve space, as this is a free hosting.
            //As long as all files are in place, everything should be fine.
            //However if error is thrown, no mechanism to course-correct. Sorry!!!
            Logger.getLogger(CoordinatesLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Latlong> getCoordinatePoints() {
        return coordinatePoints;
    }
}
