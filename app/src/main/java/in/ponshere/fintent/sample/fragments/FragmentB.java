package in.ponshere.fintent.sample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.FIntentControllable;
import in.ponshere.fintent.Factory;
import in.ponshere.fintent.sample.BaseFragment;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingFragmentB;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public class FragmentB extends BaseFragment<BindingFragmentB> {

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Factory.getInstance().getController((FIntentControllable) getActivity())
                        .startFragment(new FIntent(FragmentC.class));
            }
        });
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_b;
    }
}
