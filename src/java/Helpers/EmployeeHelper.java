/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Models.Employee;
import ead_assignment.HibernateUtil;
import Models.Role;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Nutt
 */
public class EmployeeHelper {

    /**
     * Get all employees
     *
     * @param startId
     * @param endId
     * @return employee list as java.util.List
     */
    public List getEmployees(int startId, int endId) {
        List<Employee> empList = null;
        Transaction tx = null;
        Session session = HibernateUtil.getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("select e from Employee as e");
            empList = (List<Employee>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return empList;
    }

    /**
     * Add New Employee Method
     *
     * @param name
     * @param roleId
     * @return employee object
     */
    public Employee createEmployee(String name, int roleId) {
        Employee employee = null;
        Transaction tx = null;
        Session session = HibernateUtil.getCurrentSession();
        try {
            tx = session.beginTransaction();
            employee = new Employee();
            employee.setName(name);
            if (roleId != -1) {
                Role role = (Role) session.createQuery(
                        "select r from Role as r where r.roleid = :rid"
                ).setParameter("rid", roleId).uniqueResult();
                employee.setRole(roleId);
            }
            session.save(employee);
            tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return employee;
    }

    /**
     * Update Employee Details
     *
     * @param name
     * @param empID
     */
    public Employee updateEmployee(String name, int empID) {
        Transaction tx = null;
        Session session = HibernateUtil.getCurrentSession();
        try {
            tx = session.beginTransaction();
            Employee employee = (Employee) session.createQuery(
                    "select e from Employee as e where e.employeeid = :eid"
            ).setParameter("eid", empID).uniqueResult();

            employee.setName(name);
            session.update(employee);
            tx.commit();
            return employee;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
