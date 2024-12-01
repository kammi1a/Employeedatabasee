package org.example.employeedatabasee;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        EmployeeData employeeData = new EmployeeData();

        // Создание нового сотрудника
        employeeData.createEmployee("George Black", "Analyst", 85000.0, Date.valueOf("2024-01-01"));

        // Получение сотрудника по ID
        Employee employee = employeeData.getEmployeeById(1); // Замените 1 на действующий ID из вашей базы данных
        if (employee != null) {
            employee.setPosition("Senior Analyst");
            System.out.println("Updated employee: " + employee);
        } else {
            System.out.println("Employee not found!");
        }

        // Получение всех сотрудников
        employeeData.getAllEmployees();

        // Удаление сотрудника
        employeeData.deleteEmployee(4); // Удаление сотрудника с ID 4
    }
}
