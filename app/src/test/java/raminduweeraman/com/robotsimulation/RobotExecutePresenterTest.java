package raminduweeraman.com.robotsimulation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import raminduweeraman.com.robotsimulation.presenter.RobotExecutor;
import raminduweeraman.com.robotsimulation.presenter.RobotExecutePresenter;
import raminduweeraman.com.robotsimulation.presenter.ScheduleProvider;
import raminduweeraman.com.robotsimulation.repository.RobotInstructionRepository;
import raminduweeraman.com.robotsimulation.view.RobotView;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RobotExecutePresenterTest {

    @Mock
    RobotInstructionRepository robotInstructionRepository;
    @Mock
    private RobotView robotView;
    @Mock
    private RobotExecutor robotEngine;

    private MockScheduleProvider scheduleProvider;

    private RobotExecutePresenter robotExecutePresenter;

    @Before
    public void before() throws Exception {
        scheduleProvider = new MockScheduleProvider();
        robotExecutePresenter = new RobotExecutePresenter(robotEngine, robotInstructionRepository, robotView, scheduleProvider);
    }

    @Test
    public void testReturnErrorCommand() {
        Exception exception = new Exception();
        when(robotInstructionRepository.getInstructions())
                .thenReturn(Observable.<List<String>>error(exception));
        robotExecutePresenter.executeRobot();
        InOrder inOrder = Mockito.inOrder(robotView);
        inOrder.verify(robotView, times(1)).showErrorCommand();
    }


    @Test
    public void testReturnValidOutput1() {

        List<String> commandList = new ArrayList<>();
        commandList.add("MOVE");
        when(robotInstructionRepository.getInstructions())
                .thenReturn(Observable.just(commandList));
        when(robotEngine.executeCommand(anyString())).thenReturn("MOVE");
        robotExecutePresenter.executeRobot();
        InOrder inOrder = Mockito.inOrder(robotView);
        inOrder.verify(robotView, times(1)).showRobotOutput("MOVE");
    }

    @Test
    public void testReturnValidOutput2() {
        List<String> commandList = new ArrayList<>();
        commandList.add("COMMAND");
        when(robotInstructionRepository.getInstructions())
                .thenReturn(Observable.just(commandList));
        when(robotEngine.executeCommand(anyString())).thenReturn("3,3,NORTH");
        robotExecutePresenter.executeRobot();
        InOrder inOrder = Mockito.inOrder(robotView);
        inOrder.verify(robotView, times(1)).showRobotOutput("3,3,NORTH");
    }

    public class MockScheduleProvider implements ScheduleProvider {
        @Override
        public Scheduler getIOScheduler() {
            return Schedulers.trampoline();
        }

        @Override
        public Scheduler getMainScheduler() {
            return Schedulers.trampoline();
        }
    }
}
