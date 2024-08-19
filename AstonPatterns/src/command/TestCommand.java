package command;

public class TestCommand {

	public static void main(String[] args) {
		Robot2D robot2d = new Robot2D("Винтик");
		
		// Все команды
		StepDownCommand stepDownCommand = new StepDownCommand(robot2d); 
		StepUpCommand stepUpCommand = new StepUpCommand(robot2d);
		StepToRightCommand stepToRightCommand = new StepToRightCommand(robot2d);
		StepToLeftCommand stepToLeftCommand = new StepToLeftCommand(robot2d);
		
		//Добавление команд
		RobotCommands commands = new RobotCommands();
		commands.addCommand("left", stepToLeftCommand);
		commands.addCommand("right", stepToRightCommand);
		commands.addCommand("up", stepUpCommand);
		commands.addCommand("down", stepDownCommand);
		
		commands.toStep("left", 2);
		commands.toStep("left", 2);
		commands.toStep("right", 1);
		commands.toStep("up", 1);
		commands.toStep("down", 5);
		
		System.out.println(robot2d);
	}
}
