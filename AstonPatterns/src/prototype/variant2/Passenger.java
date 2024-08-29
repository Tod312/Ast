package prototype.variant2;

import java.util.Objects;

public class Passenger {

	private String name;
	private Ticket ticket;
	
	public Passenger(String name, Ticket ticket) {
		this.name = name;
		this.ticket = ticket;
	}

	public Passenger(Passenger passenger) {
		this.name = passenger.getName();
		this.ticket = new Ticket(passenger.ticket);
	} 
	

	public String getName() {
		return name;
	}

	public Ticket getTicket() {
		return ticket;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, ticket);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		return Objects.equals(name, other.name) && Objects.equals(ticket, other.ticket);
	}
	
	
	
}
