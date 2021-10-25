package com.mvcwebapp.webgoat.controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.stream.Collectors;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnStrInjController {
  // Vulnerable Connection String Injection Methods
  @GetMapping("/connstrinj/tp/{tpParam}")
  public Object methodTP(@PathVariable("tpParam") String tpParam) throws SQLException, ClassNotFoundException, ParseException {
    Connection conn = null;
    Object resObj = null;
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      String connStr = "jdbc:sqlserver://JOAOCOELHO-LT;databaseName=TestDB;integratedSecurity=true";
      conn = DriverManager.getConnection(connStr);
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM Table1");
      ResultSet rs = ps.executeQuery();
      String resStr = "[";

      while (rs.next()) {
        resStr += String.format("{\"ID\":\"%s\",\"Name\":\"%s\"},", rs.getString("ID"), rs.getString("Name"));
      }

      resStr = resStr.substring(0, resStr.length() - 1);
      resStr += "]";
      resObj = new JSONParser(resStr).parse();
    }
    catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    finally {
      conn.close();
    }
    return resObj;
  }
	
  @GetMapping("/connstrinj/tp2/{tpParam2}")
  public Object methodTP2(@PathVariable("tpParam2") String tpParam2) throws SQLException, ClassNotFoundException, ParseException {
		Connection conn = null;
    Object resObj = null;
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      String connStr = "jdbc:sqlserver://JOAOCOELHO-LT;databaseName=TestDB";
      conn = DriverManager.getConnection(connStr, "sa", "Cx!234567");
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM Table1");
      ResultSet rs = ps.executeQuery();
      ArrayList<Hashtable<String, String>> listRes = new ArrayList<Hashtable<String, String>>();
      
      while (rs.next()) {
        Hashtable<String, String> ht = new Hashtable<>();
        ht.put("\"ID\"", "\"" + rs.getString("ID") + "\"");
        ht.put("\"Name\"", "\"" + rs.getString("Name") + "\"");
        listRes.add(ht);
      }
      
      String listToStr = listRes.stream().map(Object::toString).collect(Collectors.joining(", "));
      String resStr = String.format("[%s]", listToStr.replace("=", ":"));
      resObj = new JSONParser(resStr).parse();
    }
    catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    finally {
      conn.close();
    }
    return resObj;
  }

	@GetMapping("/connstrinj/tn/{tnParam}")
  public String methodTN4(@PathVariable("tnParam4") String tnParam4) {
    return tnParam4;
  }
	@PostMapping("/connstrinj/tn2/{tnParam2}")
  public String methodTN5(@PathVariable("tnParam2") String tnParam2) {
    return tnParam2;
  }
}