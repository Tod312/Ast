package prototype.variant2;

import java.util.Objects;

public class Ticket {

	private Integer number;

	public Ticket(Integer number) {
		super();
		this.number = number;
	}
	
	public Ticket(Ticket ticket) {
		this.number = ticket.number;
	}

	public Integer getNumber() {
		return number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(number, other.number);
	}

	
}
