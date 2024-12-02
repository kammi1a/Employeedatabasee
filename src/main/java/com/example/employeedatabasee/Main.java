package com.example.employeedatabasee;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeData employeeData = new EmployeeData();

        Employee newEmployee1 = new Employee(1, "John Doe", "Developer", 75000, java.sql.Date.valueOf("2021-02-16"));
        Employee newEmployee2 = new Employee(2, "Jane Smith", "Manager", 95000, java.sql.Date.valueOf("2019-03-22"));
        Employee newEmployee3 = new Employee(3, "Alice Brown", "HR Specialist", 65000, java.sql.Date.valueOf("2018-07-11"));

        employeeData.createEmployee(newEmployee1);
        employeeData.createEmployee(newEmployee2);
        employeeData.createEmployee(newEmployee3);

        // 1. Get employee by ID 
        Employee employeeById = employeeData.getEmployeeById(1);
        if (employeeById != null) {
            System.out.println("Employee with ID 1: " + employeeById);
        } else {
            System.out.println("Employee with ID 1 not found.");
        }

        // 2. Get all employees
        List<Employee> allEmployees = employeeData.getAllEmployees();
        System.out.println("All employees: " + allEmployees);

        // 3. Update an employee's details (e.g., update salary of employee with ID 1)
        if (employeeById != null) {
            employeeById.setSalary(8500); 
            employeeData.updateEmployee(employeeById); 
            System.out.println("Updated employee: " + employeeById);
        }

        // 4. Delete an employee 
        int employeeIdToDelete = 2;
        employeeData.deleteEmployee(employeeIdToDelete);
        System.out.println("Deleted employee with ID " + employeeIdToDelete);

        // Optional: Get all employees again to confirm the deletion
        allEmployees = employeeData.getAllEmployees();
        System.out.println("All employees after deletion: " + allEmployees);
    }
}
