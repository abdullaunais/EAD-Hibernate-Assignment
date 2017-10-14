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

    public List getEmployees(int startId, int endId) {
        List<Employee> empList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select from Employee where employeeid between "+ startId +" and "+ endId);
            empList = (List<Employee>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }
    
    public void createEmployee(String name,int roleId){
        try{
            org.hibernate.Transaction tx = session.beginTransaction();
            Employee employee = new Employee();
            employee.setName(name);
            if(roleId != -1){
                Role role = (Role) session.createQuery(
                                "select r from Role as r where r.roleID = :rid"
                            ).setParameter("rid", roleId).uniqueResult();
                employee.setRole(roleId);
            }
            session.save(employee);
            tx.commit();
            
        }catch(RuntimeException e){
            e.printStackTrace();
        }
    }
    
    public void updateEmployee(String name,int empID){
        try{
            org.hibernate.Transaction tx = session.beginTransaction();
            Employee dbEmp =(Employee) session.createQuery(
                    "select e from Employee as e where e.employeeID = :eid"
            ).setParameter("eid", empID).uniqueResult();
            
            dbEmp.setName(name);
            session.update(dbEmp);
            tx.commit();
            
        }catch(RuntimeException e){
            e.printStackTrace();
        }
    }
}
