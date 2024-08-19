package command;

public class Robot2D {

	private String name;
	private int x = 0;
	private int y = 0;
	
	
	public Robot2D(String name) {
		this.name = name;
	}
	
	public void goRigth(int step) {
		x = x + step;
	}

	public void goLeft(int step) {
		x = x - step;
		System.out.println(x);
	}
	
	public void goUp(int step) {
		y = y + step;
	}
	
	public void goDown(int step) {
		y =y -  step;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Robot2D [name=" + name + ", x=" + x + ", y=" + y + "]";
	}
	
	
}
