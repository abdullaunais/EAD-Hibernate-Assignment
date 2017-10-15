/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ead_assignment;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Nutt
 */
@Named(value = "roleController")
@ManagedBean
@SessionScoped
public class RoleController implements Serializable {

    int startId;
    int endId;
    DataModel roleTitles;
    RoleHelper helper;
    private int recordCount = 1000;
    private int pageSize = 10;

    private Role current;
    private int selectedItemIndex;
    private String newTitle;

    /**
     * Creates a new instance of RoleController
     */
    public RoleController() {
        helper = new RoleHelper();
        startId = 1;
        endId = 50;
    }

    public RoleController(int startId, int endId) {
        helper = new RoleHelper();
        this.startId = startId;
        this.endId = endId;
    }
    
    public String getNewTitle() {
        return newTitle;
    }
    
    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }
    
    public String saveRole() {
        System.out.println("description: " + this.newTitle);

        helper = new RoleHelper();
        current = helper.createRole(this.newTitle);
        recreateModel();
        getRoles();
        return "role_list";
    }

    public Role getSelected() {
        if (current == null) {
            current = new Role();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    public DataModel getRoles() {
        helper = new RoleHelper();
        if (roleTitles == null) {
            roleTitles = new ListDataModel(helper.getRoles(startId, endId));
        }
        return roleTitles;
    }

    void recreateModel() {
        roleTitles = null;
    }

    public boolean isHasNextPage() {
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
        return "index";
    }

    public String previous() {
        startId = startId - pageSize;
        endId = endId - pageSize;
        recreateModel();
        return "index";
    }

    public int getPageSize() {
        return pageSize;
    }

    public String prepareView() {
        current = (Role) getRoles().getRowData();
        return "browse_role";
    }

    public String prepareList() {
        recreateModel();
        return "role_list";
    }
    
    public String addNew() {
        return "new_role";
    }

    public String goToEmployees() {
        return "employee_list";
    }

    public String goToRoles() {
        return "role_list";
    }

    public String goToTasks() {
        return "task_list";
    }
}
