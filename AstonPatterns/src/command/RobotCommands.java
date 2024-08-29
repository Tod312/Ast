package command;

import java.util.HashMap;
import java.util.Map;

public class RobotCommands {

	private Map<String, Command> commands = new HashMap<String, Command>();
	
	public void addCommand(String key, Command command) {
		this.commands.put(key, command);
	}
	
	
	public void toStep(String key, int steps) {
		this.commands.get(key).toStep(steps);
	}

}
