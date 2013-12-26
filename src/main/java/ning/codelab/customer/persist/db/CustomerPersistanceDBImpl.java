package ning.codelab.customer.persist.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ning.codelab.customer.Customer;
import ning.codelab.customer.persist.CustomerPersistance;

public class CustomerPersistanceDBImpl implements CustomerPersistance {

	
	//TODO: Dummy implementation to verify the interfaces. Needs to be replaced by the MySQL based JDBI implementation.
	private Map<Integer, Customer> memDB = new HashMap<Integer, Customer>();

	@Override
	public Customer getCustomerWithId(int id) {
		return memDB.get(id);
	}

	@Override
	public void addCustomer(int id, String name, String address) {
		memDB.put(id, new Customer(name, id, address));
	}

	@Override
	public void updateCustomer(int id, Customer customer) {
		memDB.put(id, customer);
	}

	@Override
	public void deleteCustomer(int id) {
		memDB.remove(id);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return new ArrayList<Customer>(memDB.values());
	}

	@Override
	public void clear() {
		memDB.clear();
	}
	
	
}
