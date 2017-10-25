/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Helpers.EmployeeHelper;
import Helpers.RoleHelper;
import Models.Employee;
import Models.Role;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    RoleHelper rHelper;
    private int recordCount = 1000;
    private int pageSize = 50;

    private Employee current;
    private Role currentRole;
    private int selectedItemIndex;

    private String newName;
    private List<String> allRoles;
    List<Role> roleList;
    private String selectedRole;
    Role roleListSelected;

    private String updateName;

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

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public String updateEmployee() {
        System.out.println("name: " + this.updateName);

        helper = new EmployeeHelper();
        Employee updated;
        updated = helper.updateEmployee(this.updateName, current.getEmployeeid());
        recreateModel();
        getEmployees();
        current = updated;
        return "employee_list";
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public List getAllRoles() {
        return allRoles;
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    public String saveCustomer() {
        System.out.println("name: " + this.newName);
        System.out.println("role:" + this.selectedRole);
        roleList.forEach((Role obj) -> {
            if (obj.getTitle().equals(this.selectedRole)) {
                this.roleListSelected = obj;
            }
        });

        helper = new EmployeeHelper();
        current = helper.createEmployee(this.newName, roleListSelected.getRoleid());
        recreateModel();
        getEmployees();
        return "employee_list";
    }

    public Employee getSelected() {
        if (current == null) {
            current = new Employee();
            selectedItemIndex = -1;
        }
        return current;
    }

    public Role getCurrentRole() {
        return currentRole;
    }

    public DataModel getEmployees() {
        helper = new EmployeeHelper();
        if (employees == null) {
            employees = new ListDataModel(helper.getEmployees(startId, endId));
        }
        return employees;
    }

    public void getRoleList() {
        rHelper = new RoleHelper();
        roleList = rHelper.getRoles(0, 100);
        allRoles = new ArrayList<>();
        roleList.forEach((Role elem) -> {
            allRoles.add(elem.getTitle());
        });
        System.out.println("Roles Recieved");
    }

    void recreateModel() {
        employees = null;
    }

//    public void submit() {
//        result = "Submitted values: " + newName + ", " + newRole;
//        System.out.println(result);
//    }
    public boolean isHasNextPage() {
        if (employees != null) {
            recordCount = employees.getRowCount();
        } else {
            return false;
        }
        if (endId + pageSize <= recordCount) {
            return true;
        }
        return false;
    }

    public boolean isHasPreviousPage() {
        if (startId - pageSize > 0) {
            return true;
        }
        return false;
    }

    public String next() {
        startId = endId + 1;
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

    public String prepareView() {
        current = (Employee) getEmployees().getRowData();
        rHelper = new RoleHelper();
        currentRole = rHelper.getRoleById(current.getRole());
        getRoleList();
        return "browse_employee";
    }

    public String prepareList() {
        recreateModel();
        getEmployees();
        return "employee_list";
    }

    public String addNew() {
        getRoleList();
        return "new_employee";
    }

    public String goToEmployees() {
        getEmployees();
        return "employee_list";
    }

    public String goToRoles() {
        return "role_list";
    }

    public String goToTasks() {
        return "task_list";
    }
}
