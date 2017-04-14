package in.ponshere.fintent.sample.fragments.start_for_result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import in.ponshere.fintent.FIFragment;
import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS6FragmentA;
import in.ponshere.fintent.sample.fragments.navigate_to.FIntentNames;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US6FragmentA extends FIFragment<BindingUS6FragmentA> implements View.OnClickListener,FIntentNames{

    private static final int REQUEST_CODE = 1000;

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnStartForResult.setOnClickListener(this);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Start For Result");
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us6_fragment_a;
    }

    @Override
    public void onClick(View view) {
        startFragmentForResult(new FIntent(US6FragmentB.class,US6FragmentA),REQUEST_CODE);
    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == OK){
                Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
            }else if(resultCode == CANCELLED){
                Toast.makeText(getContext(), "CANCELLED", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
