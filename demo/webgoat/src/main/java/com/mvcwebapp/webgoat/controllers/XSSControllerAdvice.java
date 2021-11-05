package com.mvcwebapp.webgoat.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
    
@ControllerAdvice
public class XSSControllerAdvice {
  // Vulnerable XSS Methods
  /*@ExceptionHandler(value = Exception.class) // payload -> /erros/<img%20src=x%20onerror=alert(1)>
	@ResponseBody
  public Object methodTP(HttpServletRequest req, Exception e) throws Exception {
      String msg = e.getMessage();
      return msg;
  }

  @ExceptionHandler(value = Exception.class) // payload -> /erros/<img%20src=x%20onerror=alert(1)>
	@ResponseBody
  public String methodTP2(HttpServletRequest req, Exception e) throws Exception {
      String msg = e.getMessage();
      return msg;
  }
  
  @ExceptionHandler(value = Exception.class) // payload -> /erros/&lt;img src=x onerror=alert(1)&gt;
	@ResponseBody
  public Object methodTP3(HttpServletRequest req, Exception e) throws Exception {
      String param = req.getParameter("tpParam");
      return param;
  }*/

  @ExceptionHandler(value = Exception.class) // payload -> /erros/&lt;img src=x onerror=alert(1)&gt;
	@ResponseBody
  public Object methodTP4(HttpServletRequest req, Exception e) throws Exception {
      return e.toString();
  }

  // NOT Vulnerable XSS Methods
  /*@ExceptionHandler(value = Exception.class)
	@ResponseBody
  public Object methodTN(HttpServletRequest req, Exception e) throws Exception {
      Object url = req.getRequestURL();
      return url;
  }
  
  @ExceptionHandler(value = Exception.class)
	@ResponseBody
  public String methodTN2(HttpServletRequest req, Exception e) throws Exception {
      String url = req.getRequestURL().toString();
      return url;
  }
  
  @ExceptionHandler(value = Exception.class) // payload -> /erros/&lt;img src=x onerror=alert(1)&gt;
	@ResponseBody
  public String methodTN3(HttpServletRequest req, Exception e) throws Exception {
      ClassTest ct = new ClassTest();
      ct.setName(req.getRequestURL().toString());
      return ct.getName();
  }
  
  @ExceptionHandler(value = Exception.class) // payload -> /erros/&lt;img src=x onerror=alert(1)&gt;
	@ResponseBody
  public Object methodTP4(HttpServletRequest req, Exception e) throws Exception {
      return e;
  }
  
  @ExceptionHandler(value = Exception.class) // payload -> /erros/&lt;img src=x onerror=alert(1)&gt;
	@ResponseBody
  public Object methodTP5(HttpServletRequest req, Exception e) throws Exception {
      ClassTest ct = new ClassTest();
      ct.setName(req.getRequestURL().toString());
      return ct.toString();
  }*/
}