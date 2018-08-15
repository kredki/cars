package com.capgemini.service;

public interface OutpostService {
    public void addEmployee(long outpostId, long employeeId);

    public void removeEmployee(long outpostId, long employeeId);
}
