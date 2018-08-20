package com.capgemini.service;

/**
 * Outpost Service
 */
public interface OutpostService {
    /**
     * add employee to outpost.
     * @param outpostId Outpost id.
     * @param employeeId Employee id.
     */
    public void addEmployee(long outpostId, long employeeId);

    /**
     * Remove employee from outpost.
     * @param outpostId Outpost id.
     * @param employeeId Employee id.
     */
    public void removeEmployee(long outpostId, long employeeId);
}
