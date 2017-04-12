package in.ponshere.fintent.sample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.FIntentControllable;
import in.ponshere.fintent.Factory;
import in.ponshere.fintent.BaseFragment;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingFragmentC;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public class FragmentC extends BaseFragment<BindingFragmentC> {
    public static final String FINTENT_TAG = "FragmentC";
    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Factory.getInstance().getController((FIntentControllable) getActivity())
                        .startFragment(new FIntent(FragmentD.class).setTag(FINTENT_TAG));
//                .addFlag(FIntent.FLAGS.CLEAR_HISTORY)
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "onResume C", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_c;
    }

    @Override
    public String uniqueFIntentTag() {
        return "FRAG C";
    }
}
