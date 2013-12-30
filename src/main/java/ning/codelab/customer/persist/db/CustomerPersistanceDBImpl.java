package ning.codelab.customer.persist.db;

import java.util.List;

import ning.codelab.customer.Customer;
import ning.codelab.customer.DBConfig;
import ning.codelab.customer.persist.CustomerPersistance;

import org.apache.log4j.Logger;
import org.skife.jdbi.v2.DBI;

import com.google.inject.Inject;


public class CustomerPersistanceDBImpl implements CustomerPersistance {

	private final DBConfig db;
	private CustomerDAO dao;

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
		this.dao = dbAccess.open(CustomerDAO.class);
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
		return dao.findById(id);
	}

	@Override
	public void addCustomer(int id, String name, String address) {
		dao.insert(id, name, address);
	}

	@Override
	public void updateCustomer(int id, Customer customer) {
		String name = customer.getName();
		String address = customer.getAddress();
		dao.update(id, name, address);
	}

	@Override
	public void deleteCustomer(int id) {
		dao.delete(id);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return dao.showAll();
	}

	@Override
	public void clear() {
		dao.clearAllRecords();
	}
}
