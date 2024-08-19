package prototype.variant1;

import java.util.Objects;

public class Driver implements Cloneable{

	private String name;
	private Integer age;
	private Car car;

	public Driver(String name, Integer age, Car car) {
		this.name = name;
		this.age = age;
		this.car = car;
	}
	
	

	@Override
	protected Driver clone() throws CloneNotSupportedException {
		Driver copy =(Driver) super.clone();
		copy.car = (Car) this.car.clone();
		return copy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, car, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		return Objects.equals(age, other.age) && Objects.equals(car, other.car) && Objects.equals(name, other.name);
	}
	
	
	
}
