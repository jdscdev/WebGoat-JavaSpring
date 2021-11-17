package com.mvcwebapp.webgoat.controllers;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@RequestMapping("/service")
public class ServiceController
{
  @RequestMapping(value = "/tp/{tpParam}")
  @ResponseBody
  public String methodTP(@PathVariable("tpParam") String tpParam)
  {
    return tpParam;
  }
  // NOT Vulnerable XSS Methods
  @RequestMapping(value = "/tn/{tnParam}")
  public Object methodTN(@PathVariable(value="tnParam") String tnParam) {
    return "index";
  }
}
