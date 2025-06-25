package com.caching.services;

import com.caching.entities.Employee;
import com.caching.entities.SalaryAccount;

public interface SalaryAccountService {
    void createAccount(Employee employee);

    SalaryAccount incrementBalance(Long accountId);
}
