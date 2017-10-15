/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import ead_assignment.HibernateUtil;
import Models.Task;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Nutt
 */
public class TaskHelper {

    Session session = null;

    public TaskHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
     * Get All Tasks
     *
     * @param startID
     * @param endID
     * @return list of tasks
     */
    public List getTasks(int startID, int endID) {
        List<Task> taskList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select from Task where taskid between " + startID + " and " + endID);
            taskList = (List<Task>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Add New task
     *
     * @param desc
     * @return task object
     */
    public Task createTask(String desc) {
        Task task = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            task = new Task();
            task.setDescription(desc);
            Query cq = session.createSQLQuery("insert into Task values (default, '" + task.getDescription() + "', null)");
            cq.executeUpdate();

            //session.save(employee);
            tx.commit();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return task;
    }

    /**
     * Update Task
     * @param description
     * @param taskID 
     */
    public void updateTask(String description, int taskID) {
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Task dbRole = (Task) session.createQuery(
                    "select t from Task as t where t.taskid = :eid"
            ).setParameter("eid", taskID).uniqueResult();

            dbRole.setDescription(description);
            session.update(dbRole);
            tx.commit();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
