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
    
    /**
     * Creates a new instance of RoleController
     */
    public RoleController() {
     helper = new RoleHelper();
        startId = 1;
        endId = 10;
    }

    public RoleController(int startId, int endId) {
        helper = new RoleHelper();
        this.startId = startId;
        this.endId = endId;
    }

    public Role getSelected() {
        if (current == null) {
            current = new Role();
            selectedItemIndex = -1;
        }
        return current;
    }


    public DataModel getRoleTitles() {
        if (roleTitles == null) {
            roleTitles = new ListDataModel(helper.getRoleTitles(startId, endId));
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
        if (startId-pageSize > 0) {
            return true;
        }
        return false;
    }

    public String next() {
        startId = endId+1;
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

    public String prepareView(){
        current = (Role) getRoleTitles().getRowData();
        return "browse";
    }
    public String prepareList(){
        recreateModel();
        return "index";
    }
}
