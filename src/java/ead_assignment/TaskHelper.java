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

    public List getTasks(int startID, int endID) {
        List<Task> taskList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select from Task where taskid between "+ startID +" and "+ endID);
            taskList = (List<Task>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskList;
    }
}
