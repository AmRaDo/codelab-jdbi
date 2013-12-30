package ning.codelab.customer.persist.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import ning.codelab.customer.Customer;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class CustomerMapper implements ResultSetMapper<Customer>{

	@Override
	public Customer map(int index, ResultSet rs, StatementContext ctxt)	throws SQLException {
		return new Customer(rs.getString("c_name"),rs.getInt("c_id"),rs.getString("c_address"));
	}
}
