package raminduweeraman.com.robotsimulation.model;


import javax.inject.Inject;

import raminduweeraman.com.robotsimulation.exceptions.RobotCommandException;

public class RobotEngine {
    private Position position;

    @Inject
    public RobotEngine(){

    }

    public void move() {
        if (position.getDirection().equals(Direction.NORTH)) {
            position.updatePosition(0, 1);
        } else if (position.getDirection().equals(Direction.SOUTH)) {
            position.updatePosition(0, -1);
        } else if (position.getDirection().equals(Direction.EAST)) {
            position.updatePosition(1, 0);
        } else if (position.getDirection().equals(Direction.WEST)) {
            position.updatePosition(-1, 0);
        }
    }

    public void left() {
        Direction newDirection = getPosition().getDirection().changeDirection(-1);
        getPosition().setDirection(newDirection);
    }

    public void right() {
        Direction newDirection = getPosition().getDirection().changeDirection(1);
        getPosition().setDirection(newDirection);
    }

    public String report() {
        return position.toString();
    }

    public void placeRobot(Position position, SquareTable table) {
        if (table == null) {
            throw new RobotCommandException(("Table not found"));
        }

        if (position == null) {
            throw new RobotCommandException("Position not found");
        }

        if (!table.validatePosition(position)) {
            throw new RobotCommandException("Invalid position");
        }
        position.setMaxX(table.getX());
        position.setMaxY(table.getY());
        setPosition(position);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
