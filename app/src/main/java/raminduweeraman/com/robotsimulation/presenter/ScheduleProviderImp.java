package raminduweeraman.com.robotsimulation.presenter;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ScheduleProviderImp implements ScheduleProvider {

    public Scheduler getIOScheduler(){
       return Schedulers.io();
    }

    public Scheduler getMainScheduler(){
        return AndroidSchedulers.mainThread();
    }
}

