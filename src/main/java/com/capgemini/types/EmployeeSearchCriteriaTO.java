package com.capgemini.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * TO for employee seatch criteria
 */
@Getter
@Setter
@AllArgsConstructor
public class EmployeeSearchCriteriaTO {
    Long outpostId;
    Long carId;
    Long positionId;
}
