package in.ponshere.fintent.sample.fragments.navigate_to;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.FIntentFragment;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS3FragmentDetails;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US3FragmentDetails extends FIntentFragment<BindingUS3FragmentDetails> implements View.OnClickListener, FIntentNames {

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnFinish.setOnClickListener(this);
        binding.btnFrutisList.setOnClickListener(this);
        binding.btnVegList.setOnClickListener(this);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us3_fragment_details;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnFinish) {
            finish();
        } else if (id == R.id.btnFrutisList) {
            navigateTo(FRUITS_SCREEN);
        } else if (id == R.id.btnVegList) {
            navigateTo(VEGETABLES_SCREEN);
        }
    }
}
