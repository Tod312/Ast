package command;

public class StepUpCommand implements Command{

	private Robot2D robot2d;

	public StepUpCommand(Robot2D robot2d) {
		this.robot2d = robot2d;
	}

	@Override
	public void toStep(int steps) {
		this.robot2d.goUp(steps);
		System.out.println("Робот %s шагнул на %d вверх".formatted(robot2d.getName(), steps));
		
	}
	
	
}
