package composite;

public class Page implements LibraryComponent{

	private Integer number;

	public Page(Integer number) {
		this.number = number;
	}

	@Override
	public void display() {
		System.out.println("    Page number: " + number);
		
	}

	public Integer getName() {
		return number;
	}
	
	
}
