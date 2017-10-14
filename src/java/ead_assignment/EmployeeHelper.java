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
public class EmployeeHelper {
        Session session = null;

    public EmployeeHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List getEmployees() {
        List<Employee> empList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from employee as employee");
            empList = (List<Employee>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }
}
