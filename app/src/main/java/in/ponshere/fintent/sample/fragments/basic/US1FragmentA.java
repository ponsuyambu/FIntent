package in.ponshere.fintent.sample.fragments.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import in.ponshere.fintent.FIFragment;
import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS1FragmentA;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US1FragmentA extends FIFragment<BindingUS1FragmentA> implements View.OnClickListener{

    public static final String FINTENT_TAG = "US1FragmentA";

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(this);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Basics");
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us1_fragment_a;
    }

    @Override
    public void onClick(View view) {
        startFragment(new FIntent(US1FragmentB.class));
    }

    @Override
    public String uniqueFIntentTag() {
        return FINTENT_TAG;
    }
}
