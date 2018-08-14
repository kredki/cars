package com.capgemini.service;

import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OutpostTO;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeTO> findEmployeeByOutpost(long outpostId);

    public List<EmployeeTO> findCaretakerByOutpost(OutpostTO outpost, CarTO car);

    public void addCar(long caretakerId, CarTO car);
}
