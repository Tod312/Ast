package command;

public class StepToLeftCommand implements Command{

	private Robot2D robot2d;

	public StepToLeftCommand(Robot2D robot2d) {
		this.robot2d = robot2d;
	}

	@Override
	public void toStep(int steps) {
		robot2d.goLeft(steps);
		System.out.println("Робот %s шагнул на %d влево".formatted(robot2d.getName(), steps));
		
	}
	
	
}
