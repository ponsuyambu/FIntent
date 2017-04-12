package in.ponshere.fintent.sample.fragments.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.BaseFragment;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS1FragmentB;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US1FragmentB extends BaseFragment<BindingUS1FragmentB> implements View.OnClickListener{

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us1_fragment_b;
    }

    @Override
    public void onClick(View view) {

    }
}
