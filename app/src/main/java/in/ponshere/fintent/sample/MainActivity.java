package in.ponshere.fintent.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.FIntentController;
import in.ponshere.fintent.FIntentFactory;
import in.ponshere.fintent.IFIntentActivity;
import in.ponshere.fintent.sample.fragments.basic.US1FragmentB;

public class MainActivity extends AppCompatActivity implements IFIntentActivity {
    FIntentController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = FIntentFactory.getInstance().createFIntentController(this, R.id.rlContainer);
        controller.startFragment(new FIntent(US1FragmentB.class, "top_fragment"));
    }

    @Override
    public void onBackPressed() {
        controller.onBackPressed();
    }

    @Override
    public void callSuperBackPressed() {
        super.onBackPressed();
    }
}
