package raminduweeraman.com.robotsimulation.presenter;

import io.reactivex.Scheduler;

public interface ScheduleProvider {
    Scheduler getIOScheduler();
    Scheduler getMainScheduler();
}
