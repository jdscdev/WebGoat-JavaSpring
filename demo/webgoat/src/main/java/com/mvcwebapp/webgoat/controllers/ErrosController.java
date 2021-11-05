package com.mvcwebapp.webgoat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrosController {
  
  @RequestMapping(value = "/erros/{tpParam}")
  @ResponseBody
  public Object throwError(@PathVariable(value="tpParam") String tpParam) throws Exception {
    throw new Exception(tpParam);
  }
}
