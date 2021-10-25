package com.mvcwebapp.webgoat.controllers;

import java.util.HashMap;
import java.util.Map;
import com.mvcwebapp.webgoat.models.ClassTest;
// import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
    
@Controller
public class XSSModelController {
  // TPs XSS
  @RequestMapping(value = "/xssmodel/tp/{tpParam}") // payload -> <img%20src=x%20onerror=alert(1)>
  public String methodTP(@RequestBody String tpParam, Model model) {
    Map<String, String> map = new HashMap<>();
    map.put("tpParam", tpParam);
    model.mergeAttributes(map);
		return "index";
  }
  @RequestMapping(value = "/xssmodel/tp/")
  public String methodTP1(@RequestBody Model model) {
		return "index";
  }
	@GetMapping(value = "/xssmodel/tp2/{tpParam2}", produces = "text/html")
  public String methodTP2(@PathVariable(value="tpParam2") String tpParam2, Model model) {
    model.addAttribute("tpParam", "TP2 - " + tpParam2);
		return "index";
  }
  // @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  // @GetMapping(value = "/xssmodel/tp3/{tpParam3}")
  // public String methodTP3(@PathVariable(value="tpParam3") String tpParam3, Model model) {
  //  model.addAttribute("tpParam", "TP3 - " + tpParam3);
	// 	return "index";
  // }
	@GetMapping(value = "/xssmodel/tp4/{tpParam4}", produces = "application/json")
  public ModelAndView methodTP4(@PathVariable(value="tpParam4") String tpParam4) {
    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("tpParam", "TP4 - " + tpParam4);
    return modelAndView;
  }
	@PostMapping(value = "/xssmodel/tp5/{tpParam5}", produces = "application/json")
  public String methodTP5(@PathVariable(value="tpParam5") String tpParam5, Model model) {
    model.addAttribute("tpParam", "TP5 - " + tpParam5);
    return "index";
  }
  @ResponseBody
  @RequestMapping(value = "/xssmodel/tp6/{tpParam6}")
  public String methodTP6(@PathVariable(value="tpParam6") String tpParam6, Model model) {
    model.addAttribute("tnParam", "TP6 - " + tpParam6);
		return tpParam6;
  }
  // @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  // @GetMapping(value = "/xssmodel/tp7/{tpParam7}")
  // public String methodTP7(String tpParam7) {
  //  model.addAttribute("tnParam", "TN - " + tpParam7);
  //  return "index";
  // }

  // TNs XSS
  @RequestMapping(value = "/xssmodel/tn/{tnParam}")
  @ResponseBody
  public Map<String, Object> methodTN(@PathVariable(value="tnParam") String tnParam) {
    Map<String, Object> map = new HashMap<>();
    map.put("tpParam", tnParam);
		return map;
  }
  @RequestMapping(value = "/xssmodel/tn2/{tnParam2}", produces = "application/json")
  public Map<String, Object> methodTN2(@PathVariable(value="tnParam2") String tnParam2) {
    Map<String, Object> map = new HashMap<>();
    map.put("tpParam", tnParam2);
		return map;
  }
  @ResponseBody
  @RequestMapping(value = "/xssmodel/tn3/{tnParam3}", produces = "application/json")
  public ClassTest methodTN3(@PathVariable(value="tnParam3") String tnParam3) {
    ClassTest ct = new ClassTest();
    ct.setName(tnParam3);
		return ct;
  }
}