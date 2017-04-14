package in.ponshere.fintent.sample.fragments.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.FIFragment;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS1FragmentB;
import in.ponshere.fintent.sample.fragments.navigate_to.FIntentNames;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US1FragmentB extends FIFragment<BindingUS1FragmentB> implements View.OnClickListener,FIntentNames{

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
