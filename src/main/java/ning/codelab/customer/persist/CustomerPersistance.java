package ning.codelab.customer.persist;

import java.util.List;

import ning.codelab.customer.Customer;

public interface CustomerPersistance {
	public Customer getCustomerWithId(int id);

	public void addCustomer(int id, String name, String address);

	public void updateCustomer(int id, Customer customer);

	public void deleteCustomer(int id);

	public List<Customer> getAllCustomers();
	
	public void clear();
}
