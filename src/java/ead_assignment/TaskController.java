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
@Named(value = "taskController")
@ManagedBean
@SessionScoped
public class TaskController implements Serializable {

    int startId;
    int endId;
    DataModel tasks;
    TaskHelper helper;
    private int recordCount = 1000;
    private int pageSize = 50;

    private Task current;
    private int selectedItemIndex;

    /**
     * Creates a new instance of TaskController
     */
    public TaskController() {
        helper = new TaskHelper();
        startId = 1;
        endId = 10;
    }

    public TaskController(int startId, int endId) {
        helper = new TaskHelper();
        this.startId = startId;
        this.endId = endId;
    }

    public Task getSelected() {
        if (current == null) {
            current = new Task();
            selectedItemIndex = -1;
        }
        return current;
    }

    public DataModel getTasks() {
        if (tasks == null) {
            tasks = new ListDataModel(helper.getTasks(startId, endId));
        }
        return tasks;
    }

    void recreateModel() {
//        tasks = null;
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
        return "task_list";
    }

    public String previous() {
        startId = startId - pageSize;
        endId = endId - pageSize;
        recreateModel();
        return "task_list";
    }

    public int getPageSize() {
        return pageSize;
    }

    public String prepareView() {
        current = (Task) getTasks().getRowData();
        return "browse_task";
    }

    public String prepareList() {
        recreateModel();
        return "task_list";
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