package raminduweeraman.com.robotsimulation.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import raminduweeraman.com.robotsimulation.R;
import raminduweeraman.com.robotsimulation.di.DaggerRobotExecutorComponent;
import raminduweeraman.com.robotsimulation.di.RobotExecutorComponent;
import raminduweeraman.com.robotsimulation.presenter.RobotExecutePresenter;
import raminduweeraman.com.robotsimulation.presenter.RobotExecutor;
import raminduweeraman.com.robotsimulation.presenter.ScheduleProviderImp;
import raminduweeraman.com.robotsimulation.repository.RobotInstructionRepositoryImp;
import raminduweeraman.com.robotsimulation.view.RobotView;

public class MainActivity extends AppCompatActivity implements RobotView {

    private RobotExecutePresenter robotMovePresenter;
    private Button btnExecuteCommand;
    private TextView txtOutput;
    private RobotExecutor robotExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnExecuteCommand = (Button) findViewById(R.id.btnExecuteCommand);
        txtOutput = (TextView) findViewById(R.id.txtOutput);
        btnExecuteCommand.setOnClickListener(view -> {

            RobotExecutorComponent robotExecutorComponent = DaggerRobotExecutorComponent.create();
            robotExecutor = robotExecutorComponent.getRobotExecutor();

            robotMovePresenter = new RobotExecutePresenter(robotExecutor, new RobotInstructionRepositoryImp(getApplicationContext()), this,
                    new ScheduleProviderImp());
            robotMovePresenter.executeRobot();
        });
    }


    @Override
    public void showErrorCommand() {
        Toast.makeText(this, getString(R.string.command_error),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRobotOutput(String outputString) {
        if (!TextUtils.isEmpty(outputString)) {
            txtOutput.setText(txtOutput.getText().toString().concat("\n" + outputString));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        robotMovePresenter.clearDisposable();
    }
}
