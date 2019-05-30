package raminduweeraman.com.robotsimulation.repository;

import java.util.List;

import io.reactivex.Observable;

public interface RobotInstructionRepository {
    Observable<List<String>> getInstructions();
}
