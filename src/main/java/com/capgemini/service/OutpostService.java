package com.capgemini.service;

import com.capgemini.types.EmployeeTO;

public interface OutpostService {
    public void addEmployee(long outpostId, EmployeeTO employee);

    public void removeEmployee(long outpostId, EmployeeTO employee);
}
