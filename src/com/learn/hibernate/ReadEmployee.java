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

//class to read value from database using hibernate
public class ReadEmployee {

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
			
			//retrieve all data from database
			List<Employee> allEmployee = session.createQuery("from Employee").getResultList();
			//display result
			System.out.println("retrieving enitre data from employee table..");
			displayEmployee(allEmployee);
			
			//retrieve employee having joining date 31/12/2009
			Query q = session.createQuery( "from Employee where doj =:date");
			q.setParameter("date", DateUtils.parseDate("31/12/2009"));
			allEmployee =q.getResultList();
			
			System.out.println("retrieve employee having joining date 31/12/2009");
			displayEmployee(allEmployee);
			
			//commit transaction
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
