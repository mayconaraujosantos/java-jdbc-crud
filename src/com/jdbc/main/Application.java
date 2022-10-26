package com.jdbc.main;

import com.jdbc.dao.EmployeeDaoImplementation;
import com.jdbc.model.Employee;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

  static Logger logger = Logger.getLogger(Application.class.toString());

  static EmployeeDaoImplementation employeeDaoImplementation = new EmployeeDaoImplementation();

  public static void main(String[] args) throws SQLException {
    // addEmployee();
    // readEmployee();
    readAllEmployee();
    deleteEmployee();
  }

  /**
   * @return
   * @throws SQLException
   */
  public static Employee addEmployee() throws SQLException {
    var employees = new Employee();
    employees.setEmpName("Jorge");
    employees.setEmpAddress("Rua Clementino Zetola Junior");

    employeeDaoImplementation.add(employees);
    logger.log(Level.INFO, "save employees={} ", employees);
    return employees;
  }

  public static void readEmployee() throws SQLException {
    var employee = employeeDaoImplementation.getEmployee(1);
    System.out.println(
      employee.getEmpId() +
      " " +
      employee.getEmpName() +
      " " +
      employee.getEmpAddress()
    );
  }

  public static void readAllEmployee() throws SQLException {
    List<Employee> listOfEmplees = employeeDaoImplementation.getEmployees();
    for (Employee allEmployee : listOfEmplees) {
      System.out.println(allEmployee);
    }
  }

  public static void updateEmployee() throws SQLException {
    Employee updatedEmployee = employeeDaoImplementation.getEmployee(1);
    updatedEmployee.setEmpAddress("Asgard");
    employeeDaoImplementation.update(updatedEmployee);
  }

  public static void deleteEmployee() throws SQLException {
    employeeDaoImplementation.delete(1);
  }
}
