package in.ponshere.fintent.sample.fragments.reuse_instance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.FIFragment;
import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS4FragmentC;
import in.ponshere.fintent.sample.fragments.navigate_to.FIntentNames;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US4FragmentC extends FIFragment<BindingUS4FragmentC> implements View.OnClickListener, FIntentNames{

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
            startFragment(new FIntent(in.ponshere.fintent.sample.fragments.reuse_instance.US4FragmentA.NAME,US4FragmentC)
                    .addFlag(FIntent.FLAGS.CLEAR_HISTORY));
        }else if(id == R.id.btnNewInstance) {
            startFragment(new FIntent(US4FragmentA.class,US4FragmentC));
        }

    }

}
