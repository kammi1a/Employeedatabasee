package org.example.employeedatabasee;

import java.sql.*;

public class EmployeeData {
    private static final String URL = "jdbc:postgresql://localhost:5432/employee_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    // Получаем соединение с базой данных
    public Connection getConnection() throws SQLException {
        try {
            // Загружаем драйвер PostgreSQL
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found.", e);
        }
    }

    // Создание нового сотрудника
    public void createEmployee(String name, String position, double salary, Date hireDate) {
        String query = "INSERT INTO employee (name, position, salary, hire_date) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);
            stmt.setDate(4, hireDate);
            stmt.executeUpdate();

            System.out.println("New employee created: Name: " + name + ", Position: " + position + ", Salary: " + salary);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение сотрудника по ID
    public Employee getEmployeeById(int id) {
        String query = "SELECT * FROM employee WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date")
                );
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Получение всех сотрудников
    public void getAllEmployees() {
        String query = "SELECT * FROM employee";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("All Employees:");
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date")
                );
                System.out.println(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление сотрудника по ID
    public void deleteEmployee(int id) {
        String query = "DELETE FROM employee WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Employee with ID " + id + " has been deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Обновление информации о сотруднике
    public void updateEmployeePosition(int id, String newPosition) {
        String query = "UPDATE employee SET position = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, newPosition);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Employee with ID " + id + " position updated to: " + newPosition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
