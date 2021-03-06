package ning.codelab.customer.persist;

import java.util.List;

import ning.codelab.customer.Customer;

public interface CustomerPersistance {
	public Customer getCustomerWithId(int id);

	public int addCustomer(int id, String name, String address);

	public int updateCustomer(int id, Customer customer);

	public int deleteCustomer(int id);

	public List<Customer> getAllCustomers();

	public int clear();
}
