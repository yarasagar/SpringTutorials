package com.webservice.rest;

import javax.ws.rs.GET;  
import javax.ws.rs.Path;  
import javax.ws.rs.PathParam;  
import javax.ws.rs.core.Response;  
@Path("/hello")  
public class HelloService{  
    @GET  
    @Path("/{param}")  
    public Response getMsg(@PathParam("param") String msg) {  
        String output = "Welcome : " + msg;  
        return Response.status(200).entity(output).build();  
    }  
    @GET
    @Path("/{year}/{month}/{date}")
    public Response getDays(
    		@PathParam("year") String y,
    		@PathParam("month") String m,
    		@PathParam("date") String d
    		){
    	String out = y + "/" + m + "/" + d;
    	return Response.status(200).entity(out).build();
    }
} 
