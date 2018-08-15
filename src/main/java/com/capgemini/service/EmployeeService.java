package com.capgemini.service;

import com.capgemini.types.EmployeeTO;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeTO> findEmployeeByOutpost(long outpostId);

    public List<EmployeeTO> findCaretakerByOutpost(long outpostId, long carId);

    public void addCar(long caretakerId, long carId);
}
