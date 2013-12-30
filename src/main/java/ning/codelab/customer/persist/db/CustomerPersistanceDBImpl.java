package ning.codelab.customer.persist.db;

import java.util.List;

import ning.codelab.customer.Customer;
import ning.codelab.customer.DBConfig;
import ning.codelab.customer.persist.CustomerPersistance;

import org.apache.log4j.Logger;
import org.skife.jdbi.v2.DBI;

import com.google.inject.Inject;


public class CustomerPersistanceDBImpl implements CustomerPersistance {

	// TODO: Dummy implementation to verify the interfaces. Needs to be replaced
	// by the MySQL based JDBI implementation.
	//private Map<Integer, Customer> memDB = new HashMap<Integer, Customer>();
	
	private final DBConfig db;
	private MyDao dao;
	
	private static Logger logger= Logger.getLogger(CustomerPersistanceDBImpl.class);
	
	@Inject
	public CustomerPersistanceDBImpl(DBConfig dbase) {
		this.db=dbase;
		logger.info("URL = " + db.getUrl()+db.getDataBase()+
				" \t USER = " + db.getUser() +" \t PASS = " + db.getPass());
		makeConnection();
		createTable();
	}
	
	private void makeConnection() {
		DBI dbAccess = new DBI(db.getUrl()+db.getDataBase(), db.getUser(), db.getPass());
		this.dao = dbAccess.open(MyDao.class);
	}

	private void createTable() {
		try {
			logger.info("Checking for is table present or not ");
			dao.checkCount();
		} catch (Exception e) {
			logger.info("Create table as it is not present ");
			dao.create();
		}
	}

	@Override
	public Customer getCustomerWithId(int id) {
		//return memDB.get(id);
		return dao.findById(id);
	}

	@Override
	public void addCustomer(int id, String name, String address) {
		//memDB.put(id, new Customer(name, id, address));
		dao.insert(id, name, address);
	}

	@Override
	public void updateCustomer(int id, Customer customer) {
		//memDB.put(id, customer);
		String name = customer.getName();
		String address = customer.getAddress();
		dao.update(id, name, address);
	}

	@Override
	public void deleteCustomer(int id) {
		//memDB.remove(id);
		dao.delete(id);
	}

	@Override
	public List<Customer> getAllCustomers() {
		//return new ArrayList<Customer>(memDB.values());
		return dao.showAll();
	}

	@Override
	public void clear() {
		//memDB.clear();
		dao.close();
	}
}
