package com.mvcwebapp.webgoat.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

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
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @GetMapping(value = "/restxss/tp3/{tpParam3}")
  public String methodTP3(@PathVariable(value="tpParam3") String tpParam3) {
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
	public String methodTN8(@PathVariable(value="tpParam6") String tpParam6) {
		return methodPrivate(tpParam6);
  }
	private String methodPrivate(String paramTN) {
		return paramTN;
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
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @GetMapping(value = "/restxss/tn3/{tnParam3}")
  public String methodTN3(@PathVariable(value="tnParam3") String tnParam3) {
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
  public Map<String, String> methodTP7(@PathVariable(value="tnParam6") String tnParam6) {
    Map<String, String> map = new HashMap<>();
    map.put("id", tnParam6);
		return map;
  }
}
