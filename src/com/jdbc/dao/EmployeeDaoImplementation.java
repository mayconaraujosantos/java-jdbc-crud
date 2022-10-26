package com.jdbc.dao;

import com.jdbc.model.Employee;
import com.jdbc.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImplementation implements IEmployeeDao {

  static Connection connection = DatabaseConnection.getConnection();

  /* (non-Javadoc)
   * @see com.jdbc.dao.IEmployeeDao#add(com.jdbc.model.Employee)
   */
  @Override
  public int add(Employee employee) throws SQLException {
    final String query =
      "insert into employee(emp_name, " + "emp_address) VALUES (?, ?)";
    try (
      PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
      preparedStatement.setString(1, employee.getEmpName());
      preparedStatement.setString(2, employee.getEmpAddress());
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public void delete(int id) throws SQLException {
    final String query = "delete from employee where emp_id =?";
    try (
      PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
      preparedStatement.setInt(1, id);
      preparedStatement.executeUpdate();
    }
  }

  @Override
  public Employee getEmployee(int id) throws SQLException {
    final String query = "select * from employee where emp_id= ?";
    try (
      PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
      preparedStatement.setInt(1, id);
      return buildEmployee(preparedStatement);
    }
  }

  private Employee buildEmployee(PreparedStatement preparedStatement)
    throws SQLException {
    Employee employee = new Employee();
    ResultSet resultSet = preparedStatement.executeQuery();
    boolean check = false;

    while (resultSet.next()) {
      check = true;
      employee.setEmpId(resultSet.getInt("emp_id"));
      employee.setEmpName(resultSet.getString("emp_name"));
      employee.setEmpAddress(resultSet.getString("emp_address"));
    }

    if (check) {
      return employee;
    } else {
      return null;
    }
  }

  @Override
  public List<Employee> getEmployees() throws SQLException {
    final String query = "select * from employee";
    try (
      PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
      ResultSet resultSet = preparedStatement.executeQuery();
      List<Employee> listOfEmployee = new ArrayList<>();

      while (resultSet.next()) {
        Employee employee = new Employee();
        employee.setEmpId(resultSet.getInt("emp_id"));
        employee.setEmpName(resultSet.getString("emp_name"));
        employee.setEmpAddress(resultSet.getString("emp_address"));
        listOfEmployee.add(employee);
      }
      return listOfEmployee;
    }
  }

  @Override
  public void update(Employee employee) throws SQLException {
    final String query =
      "update employee set emp_name=?, " + "emp_address= ? where emp_id = ?";
    try (
      PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
      preparedStatement.setString(1, employee.getEmpName());
      preparedStatement.setString(2, employee.getEmpAddress());
      preparedStatement.setInt(3, employee.getEmpId());
      preparedStatement.executeUpdate();
    }
  }
}
