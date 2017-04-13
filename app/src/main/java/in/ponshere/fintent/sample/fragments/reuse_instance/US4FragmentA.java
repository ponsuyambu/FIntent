package in.ponshere.fintent.sample.fragments.reuse_instance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import in.ponshere.fintent.FIFragment;
import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS4FragmentA;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US4FragmentA extends FIFragment<BindingUS4FragmentA> implements View.OnClickListener{

    public static final String FINTENT_TAG = "US4FragmentA";
    public static final String NAME = "US4FragmentA";

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(this);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Reuse Instance");
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us4_fragment_a;
    }

    @Override
    public void onClick(View view) {
        startFragment(new FIntent(US4FragmentB.class));
    }

    @Override
    public String uniqueFIntentTag() {
        return FINTENT_TAG;
    }

    @Override
    public String uniqueFragmentName() {
        return NAME;
    }
}
