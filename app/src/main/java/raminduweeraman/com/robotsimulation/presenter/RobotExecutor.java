package raminduweeraman.com.robotsimulation.presenter;


import javax.inject.Inject;

import raminduweeraman.com.robotsimulation.model.Command;
import raminduweeraman.com.robotsimulation.model.Direction;
import raminduweeraman.com.robotsimulation.exceptions.RobotCommandException;
import raminduweeraman.com.robotsimulation.model.Position;
import raminduweeraman.com.robotsimulation.model.RobotEngine;
import raminduweeraman.com.robotsimulation.model.SquareTable;

public class RobotExecutor {

    private RobotEngine robot;
    private SquareTable table;

    @Inject
    public RobotExecutor(RobotEngine robot, SquareTable table) {
        this.robot = robot;
        this.table = table;
    }


    public void placeCommand(String commands) {
        int xValue = 0;
        int yValue = 0;
        String[] commandArgs = commands.split(",");
        try {
            xValue = Integer.parseInt(commandArgs[0]);
            yValue = Integer.parseInt(commandArgs[1]);
        } catch (Exception ex) {
            throw new RobotCommandException("Invalid argument");
        }
        Direction direction = Direction.from(commandArgs[2]);
        Position position = new Position(xValue, yValue, direction);
        robot.placeRobot(position, table);
    }

    public String otherCommand(Command currentCommand) {
        String output = "";
        if (robot.getPosition() == null) {
            return output;
        }
        if (currentCommand == null) {
            throw new RobotCommandException("Command not found");
        }
        switch (currentCommand) {
            case MOVE:
                robot.move();
                break;
            case LEFT:
                robot.left();
                break;
            case RIGHT:
                robot.right();
                break;
            case REPORT:
                output = robot.report();
                break;
        }
        return output;
    }

    public String executeCommand(String commandLine) {
        String[] commandArgs = commandLine.split(" ");
        String output = "";
        try {
            Command command = Command.valueOf(commandArgs[0]);
            if (command == Command.PLACE) {
                placeCommand(commandArgs[1]);
            } else {
                output = otherCommand(command);
            }
        } catch (IllegalArgumentException ex) {
            throw new RobotCommandException("Invalid command");
        }
        return output;
    }


    public RobotEngine getRobot() {
        return robot;
    }
}
