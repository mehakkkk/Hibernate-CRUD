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

//class to delete from database using hibernate
public class DeleteEmployee {

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
			 //delete only a single employee
			//get employee by id where id is 10
			Employee emp = session.get(Employee.class, 10);
			session.delete(emp);
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("deleted a single employee having id 10: checking records");
			//get result to check whether deleted or not
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			List<Employee>allEmployee = session.createQuery("from Employee").getResultList();
			displayEmployee(allEmployee);
			
			//delete all employees having doj as 20/10/2015
			session.createQuery("delete Employee where doj =: doj")
			.setParameter("doj",DateUtils.parseDate("20/10/2015")).executeUpdate();
			
			//commit transaction
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			//get result to check whether deleted or not
			session.beginTransaction();
			
			System.out.println("deleted employees having DOJ updated to 15/10/2015");
			
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
