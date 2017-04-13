package in.ponshere.fintent.sample.fragments.reuse_instance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.BaseFragment;
import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS4FragmentC;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US4FragmentC extends BaseFragment<BindingUS4FragmentC> implements View.OnClickListener{

    public static final String FINTENT_TAG = "US4FragmentC";

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(this);
        binding.btnNewInstance.setOnClickListener(this);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us4_fragment_c;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnNext){
            startFragment(new FIntent(US4FragmentA.NAME)
                    .setTransactionName(US4FragmentC.FINTENT_TAG)
                    .addFlag(FIntent.FLAGS.CLEAR_HISTORY));
        }else if(id == R.id.btnNewInstance) {
            startFragment(new FIntent(US4FragmentA.class));
        }

    }

    @Override
    public String uniqueFIntentTag() {
        return FINTENT_TAG;
    }
}
