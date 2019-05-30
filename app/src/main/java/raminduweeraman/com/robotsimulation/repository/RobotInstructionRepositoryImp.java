package raminduweeraman.com.robotsimulation.repository;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class RobotInstructionRepositoryImp implements RobotInstructionRepository {
    private Context context;

    public RobotInstructionRepositoryImp(Context context) {
        this.context = context;
    }

    @Override
    public Observable<List<String>> getInstructions() {
        ArrayList<String> instructions = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("commands.txt")));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                instructions.add(currentLine);
            }
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        return Observable.just(instructions);
    }
}
