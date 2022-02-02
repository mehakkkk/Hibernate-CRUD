package com.learn.hibernate;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learn.entity.Employee;

//class to insert into database
public class CreateEmployee {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
		
		try {
			//create a new session
			Session session = factory.getCurrentSession();
			//insert employee1
			Employee emp1 = new Employee("Vani","Gupta",DateUtils.parseDate("31/12/2009"));
			//insert employee2
			Employee emp2 = new Employee("Mayur","Jadhav",DateUtils.parseDate("31/12/2009"));
			//insert employee3
			Employee emp3 = new Employee("Arnav","Jain",DateUtils.parseDate("31/12/2009"));
			//insert employee4
			Employee emp4 = new Employee("Mehak","Gupta",DateUtils.parseDate("15/10/2015"));
			
			//start a transaction
			session.beginTransaction();
			
			//save to database
			session.save(emp1);
			session.save(emp2);
			session.save(emp3);
			session.save(emp4);
			
			System.out.println("successfully inserted commit the transaction");
			//commit the transaction
			session.getTransaction().commit();
		}
		catch(Exception ex){
			ex.printStackTrace();
			
		}
		finally{
			factory.close();
		}

	}

}
