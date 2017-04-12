package in.ponshere.fintent.sample.fragments.navigate_to;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import in.ponshere.fintent.BaseFragment;
import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS2FragmentA;
import in.ponshere.fintent.sample.fragments.clear_history.US2FragmentB;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US3FragmentA extends BaseFragment<BindingUS2FragmentA> implements View.OnClickListener{

    public static final String FINTENT_TAG = "US2FragmentA";

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(this);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Clear History");
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us2_fragment_a;
    }

    @Override
    public void onClick(View view) {
        startFragment(new FIntent(US2FragmentB.class));
    }

    @Override
    public String uniqueFIntentTag() {
        return FINTENT_TAG;
    }
}
