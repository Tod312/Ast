package command;

public class StepToRightCommand implements Command{

	private Robot2D robot2d;
	
	public StepToRightCommand(Robot2D robot2d) {
		this.robot2d = robot2d;
	}

	@Override
	public void toStep(int steps) {
		this.robot2d.goRigth(steps);
		System.out.println("Робот %s шагнул на %d вправо".formatted(robot2d.getName(), steps));
	}
	
	
}
