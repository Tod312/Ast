package prototype.variant2;

public class TestPrototypeConstruct {

	public static void main(String[] args) {
		Ticket ticket = new Ticket(232);
		Passenger passenger = new Passenger("Tom", ticket);
		
		Passenger copy = new Passenger(passenger);
		
		System.out.println(passenger == copy); // ссылки должны быть разными(false)
		System.out.println(passenger.getTicket() == copy.getTicket()); // ссылки должны быть разными(false)
		
		System.out.println(passenger.equals(copy)); // значения должны быть одинаковыми(true)
		System.out.println(passenger.getTicket().equals(copy.getTicket())); // ссылки должны быть одинаковыми(true)
	}
}
