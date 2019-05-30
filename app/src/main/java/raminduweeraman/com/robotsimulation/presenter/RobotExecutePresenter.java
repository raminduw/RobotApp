package raminduweeraman.com.robotsimulation.presenter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import raminduweeraman.com.robotsimulation.repository.RobotInstructionRepository;
import raminduweeraman.com.robotsimulation.view.RobotView;

public class RobotExecutePresenter {

    private RobotInstructionRepository robotInstructionRepository;
    private RobotView robotView;
    private RobotExecutor robotEngine;
    private CompositeDisposable compositeDisposable;
    private ScheduleProvider scheduleProvider;

    public RobotExecutePresenter(RobotExecutor robotEngine, RobotInstructionRepository robotInstructionRepository,
                                 RobotView robotView, ScheduleProvider scheduleProvider) {
        this.robotInstructionRepository = robotInstructionRepository;
        this.robotView = robotView;
        this.robotEngine = robotEngine;
        this.scheduleProvider = scheduleProvider;
        compositeDisposable = new CompositeDisposable();
    }

    public void executeRobot() {
        robotInstructionRepository.getInstructions()
                .subscribeOn(scheduleProvider.getIOScheduler())
                .observeOn(scheduleProvider.getMainScheduler())
                .flatMap(Observable::fromIterable)
                .flatMap(command -> executeCommand(command, robotEngine))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        robotView.showErrorCommand();
                    }

                    @Override
                    public void onNext(String output) {
                        robotView.showRobotOutput(output);
                    }
                });
    }

    private Observable<String> executeCommand(String command, RobotExecutor robotExecutor) {
        //if execute command is very heavy it should run on IO thread
        return Observable.just(robotExecutor.executeCommand(command));
    }

    public void clearDisposable() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

}
