package in.ponshere.fintent.sample.fragments.clear_history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.BaseFragment;
import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS2FragmentC;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US2FragmentC extends BaseFragment<BindingUS2FragmentC> implements View.OnClickListener{

    public static final String FINTENT_TAG = "US2FragmentC";

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(this);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us2_fragment_c;
    }

    @Override
    public void onClick(View view) {
        startFragment(new FIntent(US2FragmentD.class).addFlag(FIntent.FLAGS.CLEAR_HISTORY));
    }

    @Override
    public String uniqueFIntentTag() {
        return FINTENT_TAG;
    }
}
