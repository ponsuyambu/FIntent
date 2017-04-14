package in.ponshere.fintent.sample.fragments.reuse_instance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.FIFragment;
import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS4FragmentB;
import in.ponshere.fintent.sample.fragments.navigate_to.FIntentNames;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US4FragmentB extends FIFragment<BindingUS4FragmentB> implements View.OnClickListener,FIntentNames{

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(this);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us4_fragment_b;
    }

    @Override
    public void onClick(View view) {
        startFragment(new FIntent(US4FragmentC.class,US4FragmentB));
    }

}
