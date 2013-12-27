package ning.codelab.customer;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import ning.codelab.customer.persist.CustomerPersistance;

import com.google.inject.Inject;

@Path("cust")
public class CustomerResource {
	private final CustomerPersistance manager;

	@Inject
	CustomerResource(CustomerPersistance manager) {
		this.manager = manager;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hi there!";
	}

	@POST
	@Path("/{id}/{name}/{address}")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCustomer(final @PathParam("id") int id,
			final @PathParam("name") String name,
			final @PathParam("address") String address) {
		// If customer already exist, update info else add a new one.
		Customer customerWithId = manager.getCustomerWithId(id);
		if (customerWithId != null) {
			customerWithId.setAddress(address);
			customerWithId.setName(name);
		} else {
			manager.addCustomer(id, name, address);
		}
		return "OK";
	}

	public void cleanAllRecords() {
		manager.clear();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(final @PathParam("id") int id) {
		Customer customerWithId = manager.getCustomerWithId(id);
		if (customerWithId == null) {
			throw new WebApplicationException(404);
		}
		return customerWithId;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(final @PathParam("id") int id) {
		Customer customerWithId = manager.getCustomerWithId(id);
		if (customerWithId == null) {
			throw new WebApplicationException(404);
		}
		manager.deleteCustomer(id);
		return "Record Deleted";
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> listCustomers() {
		return manager.getAllCustomers();
	}
}
