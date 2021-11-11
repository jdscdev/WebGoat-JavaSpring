package com.mvcwebapp.webgoat.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Produces;

import com.mvcwebapp.webgoat.models.ClassTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
    
@RestController
public class RestModelController {
  // TP
	@GetMapping(value = "/restxssmodel/tp/{tpParam}", produces = "text/html") // payload -> <img%20src=x%20onerror=alert(1)>
  public String methodTP(@PathVariable(value="tpParam") String tpParam, Model model) {
    model.addAttribute("tpParam", "TP - " + tpParam);
    return model.toString();
  }
  @RequestMapping(value = "/restxssmodel/tp2/{tpParam2}")
  @ResponseBody
  public String methodTP2(@PathVariable(value="tpParam2") String tpParam2) {
    Map<String, Object> map = new HashMap<>();
    map.put("tpParam", tpParam2);
		return map.toString();
  }
  @RequestMapping(value = "/restxssmodel/tp3/{tpParam3}")
  public String methodTP3(@PathVariable(value="tpParam3") String tpParam3) {
    ClassTest ct = new ClassTest();
    ct.setName(tpParam3);
		return ct.getName();
  }
	@GetMapping(value = "/restxssmodel/tp4/{tpParam4}")
  public ModelAndView methodTP4(@PathVariable(value="tpParam4") String tpParam4) {
    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("tpParam", "TP4 - " + tpParam4);
    return modelAndView;
  }
  @RequestMapping(value = "/restxssmodel/tp5/", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String methodTP5(@RequestBody String tpParam5) {
		return tpParam5;
  }

	@GetMapping(value = "/restxssmodel/tp6/{tpParam6}", produces = "text/html")
	@ResponseBody
	public Object methodTP6(@PathVariable(value="tpParam6") String tpParam6) {
		return ResponseEntity.status(HttpStatus.OK).body(tpParam6);
	}

  // TNs XSS
  @RequestMapping(value = "/restxssmodel/tn/{tnParam}")
  public Model methodTN(@PathVariable(value="tnParam") String tnParam, Model model) {
    Map<String, String> map = new HashMap<>();
    map.put("tnParam", tnParam);
    model.mergeAttributes(map);
		return model;
  }
  @RequestMapping(value = "/restxssmodel/tn2/")
  public Model methodTN2(@RequestBody Model model) {
		return model;
  }

  @GetMapping(value = "/restxssmodel/tn3/{tnParam3}", produces = "text/html")
	@ResponseBody
	public Object methodTN3(@PathVariable(value="tnParam3") String tnParam3) {
		ClassTest ct = new ClassTest();
		ct.setName(tnParam3);
		return ct;
	}
	
	@Produces(MediaType.TEXT_HTML_VALUE)
	@GetMapping(value = "/restxssmodel/tn4/{tnParam4}")
	@ResponseBody
	public Object methodTN4(@PathVariable(value="tnParam4") String tnParam4) {
		ClassTest ct = new ClassTest();
		ct.setName(tnParam4);
		return ct;
	}
  
  @RequestMapping(value = "/restxssmodel/tn6/{tnParam6}")
  @ResponseBody
  public Map<String, Object> methodTN6(@PathVariable(value="tnParam6") String tnParam6) {
    Map<String, Object> map = new HashMap<>();
    map.put("tpParam", tnParam6);
		return map;
  }
  @RequestMapping(value = "/restxssmodel/tn7/{tnParam7}", produces = "application/json")
  public ClassTest methodTN7(@PathVariable(value="tnParam7") String tnParam7) {
    ClassTest map = new ClassTest();
    map.setName(tnParam7);
		return map;
  }
}