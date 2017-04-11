package in.ponshere.fintent.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.FIntentControllable;
import in.ponshere.fintent.FIntentController;
import in.ponshere.fintent.Factory;
import in.ponshere.fintent.sample.fragments.FragmentA;

public class MainActivity extends AppCompatActivity implements FIntentControllable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FIntentController controller = Factory.getInstance().getController(this);
        controller.setContainerId(R.id.rlContainer);
        controller.startFragment(new FIntent(FragmentA.class).setAnimate(false));

    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentById(R.id.rlContainer) instanceof BaseFragment){
            BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.rlContainer);
            if(!fragment.onBackPressed()){
                super.onBackPressed();
            }
        }else {
            super.onBackPressed();
        }
    }
}
