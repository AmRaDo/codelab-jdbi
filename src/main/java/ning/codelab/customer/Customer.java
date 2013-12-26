package ning.codelab.customer;

import org.codehaus.jackson.annotate.JsonProperty;

public class Customer {

	private String name;
	private int id;
	private String address;

	public Customer() {
	}

	public Customer(String name, int id, String address) {
		this.name = name;
		this.id = id;
		this.address = address;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
