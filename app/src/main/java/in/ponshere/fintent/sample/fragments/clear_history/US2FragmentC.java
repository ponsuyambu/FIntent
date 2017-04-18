package in.ponshere.fintent.sample.fragments.clear_history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.FIntentFragment;
import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS2FragmentC;
import in.ponshere.fintent.sample.fragments.navigate_to.FIntentNames;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US2FragmentC extends FIntentFragment<BindingUS2FragmentC> implements View.OnClickListener, FIntentNames{

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
        startFragment(new FIntent(US2FragmentD.class,US2FragmentC).addFlag(FIntent.FLAGS.CLEAR_HISTORY));
    }
}
