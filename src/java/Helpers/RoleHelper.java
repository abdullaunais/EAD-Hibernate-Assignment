/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import ead_assignment.HibernateUtil;
import Models.Role;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Nutt
 */
public class RoleHelper {

    /**
     * Get all roles
     *
     * @param startID
     * @param endID
     * @return list of roles
     */
    public List getRoles(int startID, int endID) {
        Transaction tx = null;
        Session session = HibernateUtil.getCurrentSession();
        List<Role> roleList = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("select r from Role as r");
            roleList = (List<Role>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleList;
    }

    /**
     * Add new role
     *
     * @param title
     * @return
     */
    public Role createRole(String title) {
        Transaction tx = null;
        Session session = HibernateUtil.getCurrentSession();
        Role role = null;
        try {
            tx = session.beginTransaction();
            role = new Role();
            role.setTitle(title);
            session.save(role);
            tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * Update a Role
     *
     * @param title
     * @param roleID
     */
    public Role updateRole(String title, int roleID) {
        Transaction tx = null;
        Session session = HibernateUtil.getCurrentSession();
        try {
            tx = session.beginTransaction();
            Role role = (Role) session.createQuery(
                    "select r from Role as r where r.roleid = :eid"
            ).setParameter("eid", roleID).uniqueResult();

            role.setTitle(title);
            session.update(role);
            tx.commit();
            return role;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Role getRoleById(int roleId) {
        Transaction tx = null;
        Session session = HibernateUtil.getCurrentSession();
        Role role = null;
        try {   
            tx = session.beginTransaction();
            role = (Role) session.createQuery(
                    "select r from Role as r where r.roleid = :rid"
            ).setParameter("rid", roleId).uniqueResult();
            tx.commit();
            return role;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return role;
    }
}
