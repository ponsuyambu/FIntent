package in.ponshere.fintent.sample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.BaseFragment;
import in.ponshere.fintent.FIntentControllable;
import in.ponshere.fintent.Factory;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingFragmentB;

import static android.app.Activity.RESULT_OK;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public class FragmentB extends BaseFragment<BindingFragmentB> {

    public static final String FINTENT_TAG = "FragmentB";

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().setResult(RESULT_OK);
                Factory.getInstance().getController((FIntentControllable) getActivity()).setResult(getTargetFragment(), getTargetRequestCode(), OK, null);
//                ((IFIntentFragment)getTargetFragment()).onFragmentResult(, );
//                Factory.getInstance().getController((FIntentControllable) getActivity())
//                        .startFragment(new FIntent(FragmentC.class).setTransactionName(FINTENT_TAG));
            }
        });
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_b;
    }


    @Override
    public String uniqueFIntentTag() {
        return "FRAGB";
    }
}
