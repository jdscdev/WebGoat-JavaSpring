package com.mvcwebapp.webgoat.controllers;

import java.io.IOException;
import java.util.ArrayList;
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

@RequestMapping("/restxss")
@RestController
public class ResttController {
	// Vulnerable XSS Methods
	@RequestMapping(value = "/tp/{tpParam}", method = RequestMethod.GET)
	public String methodTP(@PathVariable(value="tpParam") String tpParam) {
		return tpParam;
	}
	@GetMapping(value = "/tp2/{tpParam2}") // payload -> <img%20src=x%20onerror=alert(1)>
	public String methodTP2(@PathVariable(value="tpParam2") String tpParam2) {
		return tpParam2;
	}
	@Consumes({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@GetMapping(value = "/tp3/{tpParam3}")
	public Object methodTP3(@PathVariable(value="tpParam3") String tpParam3) {
		return tpParam3;
	}
	@RequestMapping(value = "/tp4/{tpParam4}", method = RequestMethod.GET)
	@ResponseBody
	public String methodTP4(@PathVariable(value="tpParam4") String tpParam4) {
		return tpParam4;
	}
	@RequestMapping(value = "/tp5/{tpParam5}", method = RequestMethod.GET)
	public @ResponseBody String methodTP5(@PathVariable(value="tpParam5") String tpParam5) {
		return tpParam5;
	}
	@RequestMapping(value = "/tp6/{tpParam6}")
	public String methodTP6(@PathVariable(value="tpParam6") String tpParam6) {
		return methodPrivate(tpParam6);
	}
	private String methodPrivate(String paramTN) {
		return paramTN;
	}
	@Produces("text/plain")
	@GetMapping(value = "/tp7/{tpParam7}")
	public String methodTP7(@PathVariable(value="tpParam7") String tpParam7) {
		return tpParam7;
	}
	@RequestMapping(value="/tp8/{tpParam8}")
	protected Object methodTP8(@PathVariable(value="tpParam8") String tpParam8) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(tpParam8);
	}
	@RequestMapping(value = "/tp9/{tpParam9}", produces = {"application/x-www-form-urlencoded"})
	public void methodTP9(@PathVariable("tpParam9") String tpParam9, HttpServletResponse resp) throws IOException {
	resp.getWriter().append(tpParam9);
	}

	// NOT Vulnerable XSS Methods
	@GetMapping(value = "/tn/{tnParam}", produces = "application/json")
	public String methodTN(@PathVariable(value="tnParam") String tnParam) {
	return tnParam;
	}
	@RequestMapping(value = "/tn2/{tnParam2}", produces = "text/plain")
	@ResponseBody
	public String methodTN2(@PathVariable(value="tnParam2") String tnParam2) {
	return tnParam2;
	}
	@Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@GetMapping(value = "/tn3/{tnParam3}")
	public Object methodTN3(@PathVariable(value="tnParam3") String tnParam3) {
		return tnParam3;
	}
	@GetMapping(value = "/tn4/{tnParam4}", produces = "application/octet-stream")
	public String methodTN4(@PathVariable(value="tnParam4") String tnParam4) {
	return tnParam4;
	}
	@GetMapping(value = "/tn5/{tnParam5}")
	public String methodTN5(@PathVariable(value="tnParam5") Integer tnParam5) {
	return tnParam5.toString();
	}
	@RequestMapping(value = "/tn6/{tnParam6}", method = RequestMethod.GET)
	// @ResponseBody - Map is sanitized
	public Map<String, String> methodTN6(@PathVariable(value="tnParam6") String tnParam6) {
	Map<String, String> map = new HashMap<>();
	map.put("id", tnParam6);
		return map;
	}
	@GetMapping(value = "/tn7/{tnParam7}", produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String methodTN7(@PathVariable(value="tnParam7") String tnParam7) {
		return tnParam7;
	}

	// TESTE
	@GetMapping(value = "/tn8")
	public String methodTN8() {
		String target = "ab";
		String in = "bav";
		ArrayList<String> encStr = new ArrayList<>();
		// Prob
		char arrCh[] = target.toCharArray(); 

		for(int i = 0; i < arrCh.length - 1; i++){
		char ch = arrCh[i];
		int indBegin = in.indexOf(ch);

		if (indBegin != -1) {
		for(int n = i + 1; n < arrCh.length; n++){
			char ch2 = arrCh[n];
			int indEnd = in.indexOf(ch2);

			if (indEnd != -1) {
				if (indBegin > indEnd)
					encStr.add(in.substring(indEnd, indBegin + 1));
				else
					encStr.add(in.substring(indBegin, indEnd + 1));
			}
		}
		}
	}

	// char ch2[] = encStr.toCharArray(); 

	// for (char c : ch2) {
	//   if (in.indexOf(c) != -1) {
	//     encStr += c;
	//   }
	// }

	return "";
	}

	// private void reg(String args) {
	//   Pattern pattern = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
	//   Matcher matcher = pattern.matcher("Visit W3Schools!");
	//   boolean matchFound = matcher.find();
	//   if(matchFound) {
	//     System.out.println("Match found");
	//   } else {
	//     System.out.println("Match not found");
	//   }
	// }
}
