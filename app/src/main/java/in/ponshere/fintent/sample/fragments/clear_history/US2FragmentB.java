package in.ponshere.fintent.sample.fragments.clear_history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.FIFragment;
import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS2FragmentB;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US2FragmentB extends FIFragment<BindingUS2FragmentB> implements View.OnClickListener{

    public static final String FINTENT_TAG = "US2FragmentB";

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(this);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us2_fragment_b;
    }

    @Override
    public void onClick(View view) {
        startFragment(new FIntent(US2FragmentC.class));
    }

    @Override
    public String uniqueFIntentTag() {
        return FINTENT_TAG;
    }
}
