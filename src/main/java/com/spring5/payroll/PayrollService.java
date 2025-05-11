/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.payroll;

import com.spring5.jpapagination.Employee;
import com.spring5.jpapagination.PayrollResult;
import java.util.List;

/**
 *
 * @author javaugi
 */
public class PayrollService {
    private PayrollRuleStrategy rule;
    private List<PayrollObserver> observers;

    public PayrollResult calculatePayroll(Employee employee) {
        PayrollResult result = new PayrollResult();
        result.setOvertimePay(rule.calculateOvertime(employee.getHoursWorked(), employee));
        // ... other calculations
        observers.forEach(obs -> obs.onPayrollProcessed(employee, result));
        return result;
    }    
}
