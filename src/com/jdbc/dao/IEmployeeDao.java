package com.jdbc.dao;

import com.jdbc.model.Employee;
import java.sql.SQLException;
import java.util.List;

public interface IEmployeeDao {
  public int add(Employee employee) throws SQLException;

  public void delete(int id) throws SQLException;

  public Employee getEmployee(int id) throws SQLException;

  public List<Employee> getEmployees() throws SQLException;

  public void update(Employee employee) throws SQLException;
}
