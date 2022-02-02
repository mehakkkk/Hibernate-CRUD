package com.learn.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.learn.hibernate.DateUtils;

@Entity
@Table(name="Employee")
public class Employee {
	
	@Id
	@Column(name="EmpID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empID;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name = "doj")
	@Temporal(TemporalType.DATE)    
	private Date doj;
	
	//default constructor
	public Employee()
	{
		
	}
	
	public Employee(String firstName,String lastName,Date date)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.doj = date;
	}
	
	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String toString() {
        return "Employee [id=" + empID + ", firstName=" + firstName + ", lastName=" + lastName 
                + ", dateOfJoining=" + DateUtils.formatDate(doj) + "]";
    }
	

}
