package test.ning.codelab.customer;

import ning.codelab.customer.Customer;
import ning.codelab.customer.CustomerResource;
import ning.codelab.customer.modules.CustomerServerModule;
import ning.codelab.customer.persist.CustomerPersistance;

import org.testng.annotations.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class CustomerResourceTest {
	private CustomerResource getCustomerResource() {
		Injector injector = Guice.createInjector(new CustomerServerModule());
		CustomerResource instance = injector
				.getInstance(CustomerResource.class);
		CustomerPersistance persistance = injector
				.getInstance(CustomerPersistance.class);
		persistance.clear();
		return instance;
	}

	@Test(expectedExceptions = javax.ws.rs.WebApplicationException.class)
	public void testPersistanceClearAPI() {
		CustomerResource customerResource = getCustomerResource();
		customerResource.addCustomer(1, "Cust1", "Pune");

		Customer customer = customerResource.getCustomer(1);

		assert null != customer;
		assert 1 == customer.getId();
		assert "Cust1".equals(customer.getName());
		assert "Pune".equals(customer.getAddress());

		customerResource.cleanAllRecords();
		customerResource.getCustomer(1);
	}

	@Test
	public void testCreateAndGetCustomer() {
		CustomerResource customerResource = getCustomerResource();
		customerResource.addCustomer(1, "Cust1", "Pune");

		Customer customer = customerResource.getCustomer(1);

		assert null != customer;
		assert 1 == customer.getId();
		assert "Cust1".equals(customer.getName());
		assert "Pune".equals(customer.getAddress());
	}

	@Test(expectedExceptions = javax.ws.rs.WebApplicationException.class)
	public void testDeleteCustomer() {
		CustomerResource customerResource = getCustomerResource();
		customerResource.addCustomer(1, "Cust1", "Pune");

		Customer customer = customerResource.getCustomer(1);

		assert null != customer;
		assert 1 == customer.getId();
		assert "Cust1".equals(customer.getName());
		assert "Pune".equals(customer.getAddress());

		customerResource.deleteCustomer(1);
		customer = customerResource.getCustomer(1);
	}

	@Test
	public void testUpdateCustomer() {
		CustomerResource customerResource = getCustomerResource();
		customerResource.addCustomer(1, "Cust1", "Pune");

		Customer customer = customerResource.getCustomer(1);

		assert null != customer;
		assert 1 == customer.getId();
		assert "Cust1".equals(customer.getName());
		assert "Pune".equals(customer.getAddress());

		customerResource.addCustomer(1, "updatedName", "Pune");
		customer = customerResource.getCustomer(1);

		assert null != customer;
		assert 1 == customer.getId();
		assert "updatedName".equals(customer.getName());
		assert "Pune".equals(customer.getAddress());
	}
}
