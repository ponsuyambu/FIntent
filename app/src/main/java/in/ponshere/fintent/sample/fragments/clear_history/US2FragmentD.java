package in.ponshere.fintent.sample.fragments.clear_history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.FIFragment;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS2FragmentD;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US2FragmentD extends FIFragment<BindingUS2FragmentD> implements View.OnClickListener{

    public static final String FINTENT_TAG = "US2FragmentD";

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(this);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us2_fragment_d;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public String uniqueFIntentTag() {
        return FINTENT_TAG;
    }
}
