package com.taskone.aspire.model;

public class Employee {

   
    public String name;
    public int id;
    public String managerName;
    public int managerId;
    public String stream;
    public String designation;
    public int yearsOfExperience;

    public Employee() {
    }

    public Employee(String name, int id, String managerName, int managerId, String stream, String designation,
            int yearsOfExperience) {
        this.name = name;
        this.id = id;
        this.managerName = managerName;
        this.managerId = managerId;
        this.stream = stream;
        this.designation = designation;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        return "Employee [designation=" + designation + ", id=" + id + ", managerId=" + managerId + ", managerName="
                + managerName + ", name=" + name + ", stream=" + stream + ", yearsOfExperience=" + yearsOfExperience
                + "]";
    }

    



    
}
