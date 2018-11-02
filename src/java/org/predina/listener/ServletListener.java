/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.predina.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.predina.implementation.util.CoordinatesLoader;
import org.predina.implementation.util.VehicleLocationLoader;

/**
 * Web application lifecycle listener.
 *
 * @author akinw
 */
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        CoordinatesLoader coordinates = CoordinatesLoader.getInstance();
        VehicleLocationLoader locations = VehicleLocationLoader.getInstance();
        //by this simple declaration, back-end will be forced to read coordinate files into membory
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //nothing to do here
    }
}
