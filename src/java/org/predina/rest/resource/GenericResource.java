/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.predina.rest.resource;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.predina.implementation.Implementation;
import org.predina.model.Latlong;
import org.predina.model.Vehicle;

/**
 * REST Web Service
 *
 * @author akinw
 */
@Path("/taskresource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GenericResource {
    private final Implementation impl = new Implementation();
    
    public GenericResource() {
    }
    
    @GET
    @Path("/getcoordinates")
    public Response getFromTenThousandCoordinates() {
        List<Latlong> coordinates = impl.getCordinates();
        GenericEntity<List<Latlong>> entity = new GenericEntity<List<Latlong>>(coordinates){};
        return Response.ok().entity(entity).build();
    }
    
    @GET
    @Path("/getvehiclelocations")
    public Response getVehicleLocations() {
        List<Vehicle> locations = impl.getVehicleLocations();
        GenericEntity<List<Vehicle>> entity = new GenericEntity<List<Vehicle>>(locations){};
        return Response.ok().entity(entity).build();
    }
}
