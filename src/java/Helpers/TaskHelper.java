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
import org.hibernate.Transaction;

/**
 *
 * @author Nutt
 */
public class TaskHelper {
    /**
     * Get All Tasks
     *
     * @param startID
     * @param endID
     * @return list of tasks
     */
    public List getTasks(int startID, int endID) {
        List<Task> taskList = null;
        Transaction tx = null;
        Session session = HibernateUtil.getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("select t from Task as t where taskid between " + startID + " and " + endID);
            taskList = (List<Task>) q.list();
            tx.commit();
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
        Transaction tx = null;
        Session session = HibernateUtil.getCurrentSession();
        Task task = null;
        try {
            tx = session.beginTransaction();
            task = new Task();
            task.setDescription(desc);

            session.save(task);
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
    public Task updateTask(String description, int taskID) {
        Transaction tx = null;
        Session session = HibernateUtil.getCurrentSession();
        try {
            tx = session.beginTransaction();
            Task task = (Task) session.createQuery(
                    "select t from Task as t where t.taskid = :eid"
            ).setParameter("eid", taskID).uniqueResult();

            task.setDescription(description);
            session.update(task);
            tx.commit();
            return task;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
