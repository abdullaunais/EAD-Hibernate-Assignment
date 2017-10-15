/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import ead_assignment.HibernateUtil;
import Models.Role;
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

    /**
     * Get all roles
     *
     * @param startID
     * @param endID
     * @return list of roles
     */
    public List getRoles(int startID, int endID) {
        List<Role> roleList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select from Role where roleid between " + startID + " and " + endID);
            roleList = (List<Role>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleList;
    }

    /**
     * Add new role
     * @param title
     * @return
     */
    public Role createRole(String title) {
        Role role = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            role = new Role();
            role.setTitle(title);
            Query cq = session.createSQLQuery("insert into Role values (default, '" + role.getTitle() + "')");
            cq.executeUpdate();

            //session.save(employee);
            tx.commit();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * Update a Role
     * @param title
     * @param roleID 
     */
    public void updateRole(String title, int roleID) {
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Role dbRole = (Role) session.createQuery(
                    "select r from Role as r where r.roleid = :eid"
            ).setParameter("eid", roleID).uniqueResult();

            dbRole.setTitle(title);
            session.update(dbRole);
            tx.commit();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
