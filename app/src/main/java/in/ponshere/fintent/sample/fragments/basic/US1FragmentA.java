package in.ponshere.fintent.sample.fragments.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.FIntentFragment;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS1FragmentA;
import in.ponshere.fintent.sample.fragments.navigate_to.FIntentNames;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US1FragmentA extends FIntentFragment<BindingUS1FragmentA> implements View.OnClickListener, FIntentNames {

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(this);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Basics");
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us1_fragment_a;
    }

    @Override
    public void onClick(View view) {
        startFragment(new FIntent(US1FragmentB.class, US1FragmentA));
    }

}
