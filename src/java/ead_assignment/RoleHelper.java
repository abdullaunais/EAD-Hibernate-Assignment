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
public class RoleHelper {

    Session session = null;

    public RoleHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List getRoles(int startID, int endID) {
        List<Role> roleList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery ("select from Role where roleid between "+ startID +" and "+ endID);
        roleList = (List<Role>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleList;
    }
    
    public Role createRole(String title) {
        Role role = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            role = new Role();
            role.setTitle(title);
            Query cq = session.createSQLQuery("insert into Role values (default, '" + role.getTitle()+ "')");
            cq.executeUpdate();

            //session.save(employee);
            tx.commit();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return role;
    }
}
