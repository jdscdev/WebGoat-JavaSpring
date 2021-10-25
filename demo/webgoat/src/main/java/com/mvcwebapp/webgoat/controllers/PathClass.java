package com.mvcwebapp.webgoat.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/pathclass/")
public class PathClass {
  public PathClass()
  {
  }
  // Vulnerable XSS Methods
  @GET
  public Response pathclass()
  {
    Response resp;
    Map<String, String> servMap = new HashMap<String, String>();
    
    List<String> notRunning = servMap.values().stream()
      .map(service -> service)
      .collect(Collectors.toList());

    final String errorMsg = "Services not running: " + String.join(",", notRunning);
    resp = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMsg).build();

    return resp;
  }
}