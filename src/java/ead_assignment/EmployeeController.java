/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ead_assignment;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Nutt
 */
@Named(value = "employeeController")
@ManagedBean
@SessionScoped
public class EmployeeController implements Serializable {

    int startId;
    int endId;
    DataModel employees;
    EmployeeHelper helper;
    private int recordCount = 1000;
    private int pageSize = 10;

    private Employee current;
    private int selectedItemIndex;
    
    /**
     * Creates a new instance of EmployeeController
     */
    public EmployeeController() {
    helper = new EmployeeHelper();
        startId = 1;
        endId = 50;
    }

    public EmployeeController(int startId, int endId) {
        helper = new EmployeeHelper();
        this.startId = startId;
        this.endId = endId;
    }

    public Employee getSelected() {
        if (current == null) {
            current = new Employee();
            selectedItemIndex = -1;
        }
        return current;
    }


    public DataModel getEmployees() {
        if (employees == null) {
            employees = new ListDataModel(helper.getEmployees(startId, endId));
        }
        return employees;
    }

    void recreateModel() {
        employees = null;
    }
    
    public boolean isHasNextPage() {
        if (endId + pageSize <= recordCount) {
            return true;
        }
        return false;
    }

    public boolean isHasPreviousPage() {
        if (startId-pageSize > 0) {
            return true;
        }
        return false;
    }

    public String next() {
        startId = endId+1;
        endId = endId + pageSize;
        recreateModel();
        return "employee_list";
    }

    public String previous() {
        startId = startId - pageSize;
        endId = endId - pageSize;
        recreateModel();
        return "employee_list";
    }

    public int getPageSize() {
        return pageSize;
    }

    public String prepareView(){
        current = (Employee) getEmployees().getRowData();
        return "browse_employee";
    }
    public String prepareList(){
        recreateModel();
        return "employee_list";
    }
}
