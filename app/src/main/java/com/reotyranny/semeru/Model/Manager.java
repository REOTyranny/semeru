package com.reotyranny.semeru.Model;

import java.util.List;

public class Manager extends Account{

    public Manager(String name, String username, String password, String email) {
        super(name, username, password, email, false);
    }

    private void addEmployee(Location place,Employee emp){
        place.addEmployee(emp);
    }

    private void removeEmployee(Location place,Employee emp){
        place.removeEmployee(emp);
    }

    private List<Employee> getEmpRoster(Location place){
       return place.getEmployeeRoster();
    }

}
