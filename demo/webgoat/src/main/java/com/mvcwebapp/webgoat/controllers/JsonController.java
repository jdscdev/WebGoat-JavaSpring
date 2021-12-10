package com.mvcwebapp.webgoat.controllers;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.alibaba.fastjson.JSON;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/jsonxss")
public class JsonController {
  // Not Vulnerable XSS Methods - JSON converted
  @RequestMapping(value="/tn/{tnParam}")
  @ResponseBody
  protected void methodTN(@PathVariable(value="tnParam") String tnParam, HttpServletResponse resp) throws Exception{
      try{
          JSONObject json = new JSONObject();
          json.put("tnParam", tnParam);
          PrintWriter out = resp.getWriter();
          out.write(json.toString());
          out.flush();
          out.close();
      }catch (Exception e){
          e.printStackTrace();
          throw new Exception(e.getMessage());
      }
  }
  @RequestMapping(value="/tn2/{tnParam2}")
  protected void methodTN2(@PathVariable(value="tnParam2") String tnParam2, HttpServletResponse resp) throws Exception{
		Gson gson = new Gson();
		resp.getWriter().append(gson.toJson(tnParam2));
  }
  @RequestMapping(value="/tn3/{tnParam3}")
  public String methodTN3(@PathVariable("tnParam3") String tnParam3) {
		return JSON.toJSONString(tnParam3);
  }
  @RequestMapping(value="/tn4/{tnParam4}")
  protected Object methodTN4(@PathVariable(value="tnParam4") String tnParam4) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(tnParam4));
  }
  @RequestMapping(value="/tn5/{tnParam5}")
  protected void methodTN5(@PathVariable(value="tnParam5") String tnParam5, HttpServletResponse resp) throws Exception {
		JsonElement json = JsonParser.parseString(tnParam5);
		resp.getWriter().append(json.toString());
  }
  // Content-Type
  @RequestMapping(value="/tn6/{tnParam6}")
  protected void methodTN6(@PathVariable(value="tnParam6") String tnParam6, HttpServletResponse resp) throws Exception{
    PrintWriter out = resp.getWriter();
    resp.setContentType("application/json");
    out.println(tnParam6);
  }
}
