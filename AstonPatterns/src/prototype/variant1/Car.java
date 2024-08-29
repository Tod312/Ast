package prototype.variant1;

import java.util.Objects;

public class Car implements Cloneable{

	private String brand;
	private String version;
	
	public Car(String brand, String version) {
		this.brand = brand;
		this.version = version;
	}

	
	@Override
	protected Car clone() throws CloneNotSupportedException {
		return (Car) super.clone();
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	@Override
	public int hashCode() {
		return Objects.hash(brand, version);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(version, other.version);
	}
	
	
	
}
