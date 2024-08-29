package command;

public class StepDownCommand implements Command{

	private Robot2D robot2d;

	public StepDownCommand(Robot2D robot2d) {
		this.robot2d = robot2d;
	}

	@Override
	public void toStep(int steps) {
		robot2d.goDown(steps);	
		System.out.println("Робот %s шагнул на %d вниз".formatted(robot2d.getName(), steps));
	}
	
	
}
