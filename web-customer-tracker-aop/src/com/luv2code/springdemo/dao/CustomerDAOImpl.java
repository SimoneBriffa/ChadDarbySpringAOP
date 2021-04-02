package com.luv2code.springdemo.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository //questo tag è utile alla traduzione jdbc
public class CustomerDAOImpl implements CustomerDAO {
	
	public CustomerDAOImpl() {
		
	}
	
	//bisogna fare la injection della session factory
	
	@Autowired
	private SessionFactory sessionFactory;
	//il nome della session factory deve essere lo stesso a quelle nel file xml
	
	//------------------------GET ALL

	@Override
	//transactional evita di fare manualmente l'apertura e la chiusura della sessione
	public List<Customer> getCustomers() {
		
		//1 - get the current hibernate session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		//2 - create a query
		
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", 
									Customer.class);
		
		//3 - execute query and get result list
		
		List<Customer> customers = theQuery.getResultList();
		
		//4 - return the results
		
		return customers;
		
	}
	

	//------------------------ADD

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save/update the customer
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//retrieve customer from the database
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
		
	}

	//------------------------DELETE
	
	@Override
	public void deleteCustomer(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		Query<Customer> theQuery = currentSession.createQuery("delete from Customer where id=:theCustomerId");
		
		theQuery.setParameter("theCustomerId", theId);
		
		theQuery.executeUpdate();
	}
	

}
