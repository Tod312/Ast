package prototype.variant1;

public class TestPrototype {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		Car car = new Car("KIA", "Rio");
		Driver driver = new Driver("Alex", 19, car);
		
		Driver copyDriver = driver.clone();
		
		System.out.println(driver == copyDriver); // ссылки должны быть разными(false)
		System.out.println(driver.getCar() == copyDriver.getCar()); // ссылки должны быть разными(false)
		
		System.out.println(driver.equals(copyDriver)); // значения должны быть одинаковыми(true)
		System.out.println(driver.getCar().equals(copyDriver.getCar())); // значения должны быть одинаковыми(true)
	}
}
