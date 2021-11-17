package com.mvcwebapp.webgoat.controllers;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.alibaba.fastjson.JSON;
import net.minidev.json.JSONObject;

@RestController
public class ResttController {
  // Vulnerable XSS Methods
  @RequestMapping(value = "/restxss/tp/{tpParam}", method = RequestMethod.GET)
  public String methodTP(@PathVariable(value="tpParam") String tpParam) {
    return tpParam;
  }
	@GetMapping(value = "/restxss/tp2/{tpParam2}") // payload -> <img%20src=x%20onerror=alert(1)>
  public String methodTP2(@PathVariable(value="tpParam2") String tpParam2) {
		return tpParam2;
  }
  @Consumes({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @GetMapping(value = "/restxss/tp3/{tpParam3}")
  public Object methodTP3(@PathVariable(value="tpParam3") String tpParam3) {
		return tpParam3;
  }
  @RequestMapping(value = "/restxss/tp4/{tpParam4}", method = RequestMethod.GET)
  @ResponseBody
  public String methodTP4(@PathVariable(value="tpParam4") String tpParam4) {
		return tpParam4;
  }
  @RequestMapping(value = "/restxss/tp5/{tpParam5}", method = RequestMethod.GET)
  public @ResponseBody String methodTP5(@PathVariable(value="tpParam5") String tpParam5) {
		return tpParam5;
  }
  @RequestMapping(value = "/restxss/tp6/{tpParam6}")
	public String methodTP6(@PathVariable(value="tpParam6") String tpParam6) {
		return methodPrivate(tpParam6);
  }
	private String methodPrivate(String paramTN) {
		return paramTN;
  }
  @Produces("text/plain")
  @GetMapping(value = "/restxss/tp7/{tpParam7}")
  public String methodTP7(@PathVariable(value="tpParam7") String tpParam7) {
		return tpParam7;
  }
  // NOT Vulnerable XSS Methods
	@GetMapping(value = "/restxss/tn/{tnParam}", produces = "application/json")
  public String methodTN(@PathVariable(value="tnParam") String tnParam) {
    return tnParam;
  }
	@RequestMapping(value = "/restxss/tn2/{tnParam2}", produces = "text/plain")
	@ResponseBody
  public String methodTN2(@PathVariable(value="tnParam2") String tnParam2) {
    return tnParam2;
  }
  @Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @GetMapping(value = "/restxss/tn3/{tnParam3}")
  public Object methodTN3(@PathVariable(value="tnParam3") String tnParam3) {
		return tnParam3;
  }
  @GetMapping(value = "/restxss/tn4/{tnParam4}", produces = "application/octet-stream")
  public String methodTN4(@PathVariable(value="tnParam4") String tnParam4) {
    return tnParam4;
  }
  @GetMapping(value = "/restxss/tn5/{tnParam5}")
  public String methodTN5(@PathVariable(value="tnParam5") Integer tnParam5) {
    return tnParam5.toString();
  }
  @RequestMapping(value = "/restxss/tn6/{tnParam6}", method = RequestMethod.GET)
  // @ResponseBody - Map is sanitized
  public Map<String, String> methodTN6(@PathVariable(value="tnParam6") String tnParam6) {
    Map<String, String> map = new HashMap<>();
    map.put("id", tnParam6);
		return map;
  }
  @GetMapping(value = "/restxss/tn7/{tnParam7}", produces = MediaType.TEXT_PLAIN_VALUE)
  public @ResponseBody String methodTN7(@PathVariable(value="tnParam7") String tnParam7) {
		return tnParam7;
  }
  @RequestMapping(value="/restxss/tn8/{tnParam8}")
  @ResponseBody
  protected void methodTN8(@PathVariable(value="tnParam8") String tnParam8, HttpServletResponse resp) throws Exception{
      try{
          JSONObject json = new JSONObject();
          json.put("tnParam8", tnParam8);
          PrintWriter out = resp.getWriter();
          out.write(json.toString());
          out.flush();
          out.close();
      }catch (Exception e){
          e.printStackTrace();
          throw new Exception(e.getMessage());
      }
  }
  @RequestMapping(value="/restxss/tn9/{tnParam9}")
  protected void methodTN9(@PathVariable(value="tnParam9") String tnParam9, HttpServletResponse resp) throws Exception{
		Gson gson = new Gson();
		resp.getWriter().append(gson.toJson(tnParam9));
  }
  @RequestMapping(value="/restxss/tn10/{tnParam10}")
  public String methodTN10(@PathVariable("tnParam10") String tnParam10)
  {
		return JSON.toJSONString(tnParam10);
  }
  @RequestMapping(value="/restxss/tn11/{tnParam11}")
  protected Object methodTN11(@PathVariable(value="tnParam11") String tnParam11) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(tnParam11));
  }
  @RequestMapping(value="/restxss/tn12/{tnParam12}")
  protected void methodTN12(@PathVariable(value="tnParam12") String tnParam12, HttpServletResponse resp) throws Exception {
		JsonElement json = JsonParser.parseString(tnParam12);
		resp.getWriter().append(json.toString());
  }
}
