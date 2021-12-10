package com.mvcwebapp.webgoat.controllers;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/respxss")
public class ResponseController {
  // Vulnerable XSS Methods
  @RequestMapping(value="/tp/{tpParam}")
  public String methodTP(@PathVariable("tpParam") String tpParam) {
		return tpParam;
  }
  @RequestMapping(value = "/tp2/{tpParam2}")
  public Response methodTP2(@PathVariable(value="tpParam2") String tpParam2) {
		return Response.ok(tpParam2).build();
  }
  // Content-Type
  @RequestMapping(value="/tp3/{tpParam3}")
  protected void methodTP3(@PathVariable(value="tpParam3") String tpParam3, HttpServletResponse resp) throws Exception{
    PrintWriter out = resp.getWriter();
    resp.setContentType("text/html");
    out.println(tpParam3);
  }
  @RequestMapping(value="/tp4/{tpParam4}")
  protected Object methodTP4(@PathVariable(value="tpParam4") String tpParam4) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(tpParam4);
  }

  // NOT Vulnerable XSS Methods
  @RequestMapping(value="/tn/{tnParam}")
  protected void methodTP(@PathVariable(value="tnParam") String tnParam, HttpServletResponse resp) throws Exception{
      try{
          PrintWriter out = resp.getWriter();
          out.write(tnParam);
          out.flush();
          out.close();
      }catch (Exception e){
          e.printStackTrace();
          throw new Exception(e.getMessage());
      }
  }
  @RequestMapping(value="/tn2/{tnParam2}")
  protected void methodTP2(@PathVariable(value="tnParam2") String tnParam2, HttpServletResponse resp) throws Exception{
		resp.getWriter().append(tnParam2);
  }
}
