package com.capgemini.service;

/**
 * Outpost Service
 */
public interface OutpostService {
    /**
     * add employee to outpost.
     * @param outpostId
     * @param employeeId
     */
    public void addEmployee(long outpostId, long employeeId);

    /**
     * Remove emloyee from outpost.
     * @param outpostId
     * @param employeeId
     */
    public void removeEmployee(long outpostId, long employeeId);
}
