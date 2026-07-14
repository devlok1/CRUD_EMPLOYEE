package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Scanner;

public class crud_edb {

	private static final String url = "jdbc:mysql://localhost:3306/Employeedb";
	private static final String user = "your_username";
	private static final String password = "your_password";
	static Scanner sc = new Scanner(System.in);
	static Connection c;
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection(url, user, password);
			
			while(true) {
				System.out.println("1. Add Employee");
				System.out.println("2. View Employee");
				System.out.println("3. Update Employee");
				System.out.println("4. Delete Employee");
				System.out.println("5. Exit");
				
				int ch;
				System.out.println("Enter your choice:");
				ch = sc.nextInt();
				
				switch(ch) {
				case 1:
					addEmployee();
					break;
				case 2:
					viewEmployee();
					break;
				case 3:
					updateEmployee();
					break;
				case 4:
					deleteEmployee();
					break;
				case 5:
					Exit();
					break;
				default:
					System.out.println("Invalid option");
				}
			}
		}catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void Exit() throws SQLException {
		// TODO Auto-generated method stub
		c.close();
		System.out.println("Exiting program");
		System.exit(0);
	}

	private static void deleteEmployee() throws SQLException {
		// TODO Auto-generated method stub
		
		
		System.out.println("Enter the Employee id to delete: ");
		int id = sc.nextInt();
		
		sc.nextLine();
		
		String sql = "DELETE FROM Employee WHERE id = ?";
		
		//Connection c = DriverManager.getConnection(url, user, password);
		
		PreparedStatement ps = c.prepareStatement(sql);
		
	    ps.setInt(1, id);

	    int rows = ps.executeUpdate();
	    
	    if (rows > 0) {
	        System.out.println("Employee Deleted successfully.");
	    } else {
	        System.out.println("Employee not found.");
	    }
		ps.close();
		//c.close();
	}

	private static void updateEmployee() throws SQLException {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the Employee id to update: ");
		int id = sc.nextInt();
		
		sc.nextLine();
		
		System.out.println("Enter the name of employee to update: ");
		String name = sc.nextLine();
		
		System.out.println("Enter the department the employee to udpate : ");
		String department = sc.nextLine();
		
		System.out.println("Enter the salary to update: ");
		Double salary = sc.nextDouble();
		
		String sql = "update Employee set name = ?, department = ?, salary = ? where id = ?" ;
		
		//Connection c = DriverManager.getConnection(url, user, password);
		
		PreparedStatement ps = c.prepareStatement(sql);
		
	    ps.setString(1, name);
	    ps.setString(2, department);
	    ps.setDouble(3, salary);
	    ps.setInt(4, id);

	    int rows = ps.executeUpdate();
	    
	    if (rows > 0) {
	        System.out.println("Employee updated successfully.");
	    } else {
	        System.out.println("Employee not found.");
	    }
		
		//ResultSet rs = ps.executeQuery();
		
		//rs.close();
		ps.close();
		//c.close();
	}


	private static void viewEmployee() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "Select * from Employee";
		
		//Connection c = DriverManager.getConnection(url, user, password);
		
		PreparedStatement ps = c.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	        String name = rs.getString("name");
	        String dept = rs.getString("department");
	        double salary = rs.getDouble("salary");

	        System.out.println(name + "\t" + dept + "\t" + salary);
			
		}
		rs.close();
		ps.close();
		//c.close();
		
	}


	private static void addEmployee() throws SQLException {
		// TODO Auto-generated method stub
		sc.nextLine(); //buffer value adjusts
		System.out.println("Enter the Name :");
		String name = sc.nextLine();
		System.out.println("Enter the Department name :");
		String department = sc.nextLine();
		System.out.println("Enter the Salary :");
		Double salary = sc.nextDouble();
		
		String sql = "insert into Employee(name, department, salary)" + "values(?,?,?)";
		
		//Connection c = DriverManager.getConnection(url, user, password);
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, department);
		ps.setDouble(3, salary);
		
		int rows = ps.executeUpdate();
		if(rows>0) {
			System.out.println("Data added.");
		}
		else {
			System.out.println("Failed to add.");
		}
		ps.close();
		//c.close();
		
	}

}
