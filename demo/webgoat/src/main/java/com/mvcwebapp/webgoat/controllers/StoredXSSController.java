package com.mvcwebapp.webgoat.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
    
@Controller
public class StoredXSSController {
  // inject via application.properties
  @Value("${welcome.message}")
  private String message = "Hello World";

  @RequestMapping(value = "/storedxss", method = RequestMethod.GET)
  public String index(Model model) {
      model.addAttribute("greeting", message);
      return "index";
  }
}