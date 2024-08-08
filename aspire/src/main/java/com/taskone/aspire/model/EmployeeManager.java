package com.taskone.aspire.model;

import java.util.List;

public class EmployeeManager {

    public Manager manager;
    public List<Employee> employeeList;
    
    public EmployeeManager() {
    }

    public EmployeeManager(Manager manager, List<Employee> employeeList) {
        this.manager = manager;
        this.employeeList = employeeList;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    
    
}






