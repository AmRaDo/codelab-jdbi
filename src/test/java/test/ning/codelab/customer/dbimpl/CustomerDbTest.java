package test.ning.codelab.customer.dbimpl;

import ning.codelab.customer.Customer;
import ning.codelab.customer.DBConfig;
import ning.codelab.customer.modules.CustomerServerModule;
import ning.codelab.customer.persist.db.CustomerPersistanceDBImpl;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class CustomerDbTest {

	private CustomerPersistanceDBImpl constructDbInstance()
	{
		Injector injector = Guice.createInjector(new CustomerServerModule());
		return new CustomerPersistanceDBImpl(injector.getInstance(DBConfig.class));
	}

	@Test
	public void testCustomerAddDb()
	{
		CustomerPersistanceDBImpl db = constructDbInstance();
		assert db != null;
		assert 1 == db.addCustomer(1, "Glam", "Pune"); 
		assert 2 == db.addCustomer(2, "Wipro", "Banglore"); 
		assert 3 == db.addCustomer(3, "TCS", "Mumbai");
		assert 0 == db.clear();
		db.close();
	}
	
	@Test
	public void testCustomerUpdateDb()
	{
		CustomerPersistanceDBImpl db = constructDbInstance();
		assert db != null;
		assert 1 == db.addCustomer(1, "Glam", "Pune");
		assert 2 == db.addCustomer(2, "Wipro", "Banglore");
		assert 3 == db.addCustomer(3, "TCS", "Mumbai");
		//Perform Update
		assert 3 == db.addCustomer(1, "Glam India", "Pune & Mumbai"); //Count remains same as 3
		assert 0 == db.clear();
		db.close();
	}
	
	@Test
	public void testCustomerDeleteDb()
	{
		CustomerPersistanceDBImpl db = constructDbInstance();
		assert db != null;
		assert 1 == db.addCustomer(1, "Glam", "Pune");
		assert 2 == db.addCustomer(2, "Wipro", "Banglore");
		assert 3 == db.addCustomer(3, "TCS", "Mumbai");
		//Perform Delete
		assert 2 == db.deleteCustomer(1); 	// ID present, so delete & reduce count
		assert 2 == db.deleteCustomer(100); // ID not present, count remains same
		assert 0 == db.clear();
		db.close();
	}
	
	@Test
	public void testCustomerPrintDb()
	{
		CustomerPersistanceDBImpl db = constructDbInstance();
		assert db != null;
		assert 1 == db.addCustomer(1, "Glam", "Pune"); 
		assert 2 == db.addCustomer(2, "GlamIndia", "Mumbai & Pune");
		
		Customer c1 = db.getAllCustomers().get(0);
		assert c1 != null;
		assert 1 == c1.getId();
		assert "Glam".equals(c1.getName());
		assert "Pune".equals(c1.getAddress());
		
		Customer c2 = db.getAllCustomers().get(1);
		assert c2 != null;
		assert 2 == c2.getId();
		assert "GlamIndia".equals(c2.getName());
		assert "Mumbai & Pune".equals(c2.getAddress());

		assert 0 == db.clear();		
		db.close();
	}
	
	@Test
	public void testCheckCustomerInDb()
	{
		CustomerPersistanceDBImpl db = constructDbInstance();
		assert db != null;
		assert 1 == db.addCustomer(1, "Glam", "Pune"); 
		assert 2 == db.addCustomer(2, "Wipro", "Banglore"); 
		
		Customer c = db.getCustomerWithId(2);	   // ID present, return customer 
		assert "Wipro".equals(c.getName());
		assert "Banglore".equals(c.getAddress());
		
		assert null == db.getCustomerWithId(100);  // ID not present, returns null
		assert 0 == db.clear();
		db.close();		
	}
	
	@AfterSuite
	public void destructAll()
	{
		CustomerPersistanceDBImpl db = constructDbInstance();
		assert 0 == db.clear();
		db.close();
	}
}
