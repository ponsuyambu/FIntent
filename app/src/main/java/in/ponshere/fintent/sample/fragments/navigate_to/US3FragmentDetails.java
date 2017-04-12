package in.ponshere.fintent.sample.fragments.navigate_to;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.BaseFragment;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS3FragmentDetails;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US3FragmentDetails extends BaseFragment<BindingUS3FragmentDetails> implements View.OnClickListener{

    public static final String FINTENT_TAG = "US3FragmentDetails";

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
        if(id == R.id.btnFinish){
            finish();
        }else if(id == R.id.btnFrutisList){
            navigateTo(US3FragmenCommonList.FINTENT_TAG_FRUITS_LIST);
        }else if(id == R.id.btnVegList){
            navigateTo(US3FragmenCommonList.FINTENT_TAG_VEG_LIST);
        }
    }

    @Override
    public String uniqueFIntentTag() {
        return FINTENT_TAG;
    }
}
