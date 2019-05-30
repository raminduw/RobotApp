package raminduweeraman.com.robotsimulation.di;

import dagger.Component;
import raminduweeraman.com.robotsimulation.presenter.RobotExecutor;

@Component
public interface RobotExecutorComponent {

    RobotExecutor getRobotExecutor();
}
