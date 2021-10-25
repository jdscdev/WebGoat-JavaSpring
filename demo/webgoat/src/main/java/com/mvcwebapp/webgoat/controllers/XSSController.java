package com.mvcwebapp.webgoat.controllers;

// import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
    
@Controller
public class XSSController {
  // NOT Vulnerable XSS Methods
  @RequestMapping(value = "/xss/tn/{tnParam}", method = RequestMethod.GET)
  public String methodTP(@RequestBody String tnParam) {
		return tnParam;
  }
	@GetMapping(value = "/xss/tn2/{tnParam2}", produces = "text/html")
  public String methodTN2(@PathVariable(value="tnParam2") String tnParam2) {
		return tnParam2;
  }
  // @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  // @GetMapping(value = "/xss/tn3/{tnParam3}", produces = "text/html")
  // public String methodTN3(@PathVariable(value="tnParam3") String tnParam3) {
	// 	return tnParam3;
  // }
	@GetMapping(value = "/xss/tn4/{tnParam4}", produces = "application/json")
  public String methodTN4(@PathVariable(value="tnParam4") String tnParam4) {
    return tnParam4;
  }
	@PostMapping(value = "/xss/tn5/{tnParam5}", produces = "application/json")
  public String methodTN5(@PathVariable(value="tnParam5") String tnParam5) {
    return tnParam5;
  }
  @ResponseBody
  @RequestMapping(value = "/xss/tn7/{tnParam7}", method = RequestMethod.GET)
  public String methodTN(@PathVariable(value="tnParam7") String tnParam7) {
		return tnParam7;
  }
  @RequestMapping(value = "/xss/tn8/{tnParam8}", method = RequestMethod.GET)
  public @ResponseBody String methodTN8(@PathVariable(value="tnParam8") String tnParam8) {
		return tnParam8;
  }
  // @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  // @GetMapping(value = "/xss/tn9/{tnParam9}")
  // public String methodTN9(String tnParam9) {
	// 	return tnParam9;
  // }
  @RequestMapping(value = "/xss/tn10/{tnParam10}", method = RequestMethod.GET)
	public String methodTN10(String tnParam10) {
		return methodPrivate(tnParam10);
  }
	private String methodPrivate(String paramTN) {
		return paramTN;
  }
}