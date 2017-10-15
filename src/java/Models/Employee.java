package Models;
// Generated Oct 14, 2017 3:53:01 PM by Hibernate Tools 4.3.1

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;




/**
 * Employee generated by hbm2java
 */
public class Employee  implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int employeeid;
    private String name;
    private int role;
    
    /**
     * Constructor
     * @param employeeid
     * @param name
     * @param role 
     */
    public Employee(int employeeid, String name, int role) {
       this.employeeid = employeeid;
       this.name = name;
       this.role = role;
    }

    public Employee() {
    }

	
    public Employee(int employeeid, int role) {
        this.employeeid = employeeid;
        this.role = role;
    }

   
    public int getEmployeeid() {
        return this.employeeid;
    }
    
    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public int getRole() {
        return this.role;
    }
    
    public void setRole(int role) {
        this.role = role;
    }




}

