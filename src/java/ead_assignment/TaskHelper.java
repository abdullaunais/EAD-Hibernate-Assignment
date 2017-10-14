/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ead_assignment;

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

    public List getTasks() {
        List<Task> taskList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from task as task");
            taskList = (List<Task>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskList;
    }
}
