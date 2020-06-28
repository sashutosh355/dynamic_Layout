package com.infinity_loop.dunamiclayout;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataBaseToJson {
    public static ResultSet RetrieveData() throws Exception {
        //Registering the Driver
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost/sample";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
        System.out.println("Connection established......");
        //Creating the Statement
        Statement stmt = con.createStatement();
        //Retrieving the records
        ResultSet rs = stmt.executeQuery("Select * from tbluserea");
        return rs;
    }
    public static void main(String args[]) throws Exception {
        //Creating a JSONObject object
        JSONObject jsonObject = new JSONObject();
        //Creating a json array
        JSONArray array = new JSONArray();
        ResultSet rs = RetrieveData();
        //Inserting ResutlSet data into the json object
        while(rs.next()) {
            JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
            record.put("user_id", rs.getString("user_id"));
            record.put("email", rs.getString("email"));
            record.put("userCode", rs.getString("userCode"));
            record.put("password", rs.getString("password"));
            record.put("fullname", rs.getString("fullname"));
            record.put("gender", rs.getString("gender"));
            record.put("username", rs.getString("username"));
            record.put("created_at", rs.getString("created_at"));
            record.put("avatar", rs.getString("avatar"));
            record.put("twitter", rs.getString("twitter"));
            record.put("facebook", rs.getString("facebook"));
            record.put("follower_count", rs.getString("follower_count"));
            record.put("following_count", rs.getString("following_count"));
            record.put("post_count", rs.getString("post_count"));
            record.put("bio", rs.getString("bio"));
            record.put("blogurl", rs.getString("blogurl"));
            record.put("ulanguage", rs.getString("ulanguage"));
            record.put("ucountry", rs.getString("ucountry"));
            record.put("ucity", rs.getString("ucity"));
            record.put("ubirthday", rs.getString("ubirthday"));
            record.put("coins", rs.getString("coins"));
            array.add(record);
        }
        jsonObject.put("userea", array);
        try {
            FileWriter file = new FileWriter("C:/Users/sashu/Desktop/userea.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created......");
    }
}
