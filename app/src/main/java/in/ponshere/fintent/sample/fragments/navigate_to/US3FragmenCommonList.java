package in.ponshere.fintent.sample.fragments.navigate_to;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.FIFragment;
import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS3FragmentCommonList;

/**
 * Example for handling 2 in 1 fragment
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US3FragmenCommonList extends FIFragment<BindingUS3FragmentCommonList> implements View.OnClickListener{

    public static final String FINTENT_TAG_FRUITS_LIST = "US3Fragment_Fruits";
    public static final String FINTENT_TAG_VEG_LIST = "US3Fragment_Vegetables";
    public static final String NAME_FRUITS = "FRUITS";
    public static final String NAME_VEG = "VEG";

    public static final int TYPE_FRUITS = 1;
    public static final int TYPE_VEGETABLES = 2;
    public static final String KEY_TYPE = "KEY_TYPE";

    private int type = TYPE_FRUITS;

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(this);
        if(getArguments().getInt(KEY_TYPE) == TYPE_FRUITS){
            type = TYPE_FRUITS;
            binding.btnNext.setText("Next Fragment(Vegetables List)");
            binding.lblInfo.setText("Fruits List(via Common List)");
        }else{
            type = TYPE_VEGETABLES;
            getView().setBackgroundColor(getResources().getColor(R.color.bg_fragment_c));
            binding.btnNext.setText("Next Fragment(Details)");
            binding.lblInfo.setText("Veg List(via Common List)");
        }


    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us3_fragment_base_list;
    }

    @Override
    public void onClick(View view) {
        if(type == TYPE_FRUITS){
            Bundle data = new Bundle();
            data.putInt(KEY_TYPE,TYPE_VEGETABLES);
            startFragment(new FIntent(US3FragmenCommonList.class,data));
        }else{
            startFragment(new FIntent(US3FragmentDetails.class));
        }

    }

    @Override
    public String uniqueFIntentTag() {
        if(type == TYPE_FRUITS){
            return FINTENT_TAG_FRUITS_LIST;
        }else {
            return FINTENT_TAG_VEG_LIST;
        }

    }

    @Override
    public String uniqueFragmentName() {
        if(type == TYPE_FRUITS){
            return NAME_FRUITS;
        }else {
            return NAME_VEG;
        }
    }
}
