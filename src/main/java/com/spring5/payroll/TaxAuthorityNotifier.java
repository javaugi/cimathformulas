/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.payroll;

import com.spring5.jpapagination.Employee;
import com.spring5.jpapagination.PayrollResult;

public class TaxAuthorityNotifier implements PayrollObserver {
    @Override
    public void onPayrollProcessed(Employee employee, PayrollResult result) {
        // Send tax data to government API
    }
}
