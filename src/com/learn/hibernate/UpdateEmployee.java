package com.learn.hibernate;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learn.entity.Employee;

//class to update records from database using hibernate
public class UpdateEmployee {

	public static void displayEmployee(List<Employee>allEmployee)
	{
		for(Employee emp:allEmployee)
		{
			System.out.println(emp);
		}
	}
	public static void main(String[] args) {
		
		//create sessionfactory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
		
		try {
			
			//create new session
			Session session = factory.getCurrentSession();
			
			//start transaction
			session.beginTransaction();			
			 //update only a single employee
			//get employee by id where id is 10
			Employee emp = session.get(Employee.class, 10);
			emp.setDoj(DateUtils.parseDate("23/6/2018"));
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("updated a single employee having id 10: DOJ updated to 23/6/2018");
			//get result to check whether updated or not
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			List<Employee>allEmployee = session.createQuery("from Employee").getResultList();
			displayEmployee(allEmployee);
			
			//update all employees doj to 1/1/2017 having doj as 15/10/2015
			session.createQuery("update Employee set doj =: updateDoj where doj =: prevDoj")
			.setParameter("updateDoj",DateUtils.parseDate("20/10/2015"))
			.setParameter("prevDoj", DateUtils.parseDate("15/10/2015")).executeUpdate();
			
			//commit transaction
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			//get result to check whether updated or not
			session.beginTransaction();
			
			System.out.println("updated employees having DOJ updated to 20/10/2015");
			
			allEmployee = session.createQuery("from Employee").getResultList();
			displayEmployee(allEmployee);
			
			session.getTransaction().commit();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			factory.close();
			
		}

	}

}
