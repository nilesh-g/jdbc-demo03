package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Demo03Main {
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "nilesh";
	
	static {
		try {
			Class.forName(DB_DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String sql = "SELECT * FROM students WHERE marks > ?";
			try(PreparedStatement stmt = con.prepareStatement(sql)) {
				System.out.print("Enter min marks: ");
				double minMarks = sc.nextDouble();
				stmt.setDouble(1, minMarks);
				try(ResultSet rs = stmt.executeQuery()) {
					while(rs.next()) {
						int roll = rs.getInt("roll");
						String name = rs.getString("name");
						double marks = rs.getDouble("marks");
						System.out.println(roll + ", " + name + ", " + marks);
					}
				}
			}
		} // con.close()
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
