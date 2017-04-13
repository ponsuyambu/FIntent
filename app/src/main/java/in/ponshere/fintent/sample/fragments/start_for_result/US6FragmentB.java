package in.ponshere.fintent.sample.fragments.start_for_result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.BaseFragment;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS6FragmentB;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US6FragmentB extends BaseFragment<BindingUS6FragmentB> implements View.OnClickListener{

    public static final String FINTENT_TAG = "US6FragmentB";

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnOK.setOnClickListener(this);
        binding.btnCancel.setOnClickListener(this);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us6_fragment_b;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnCancel){
            setResult(CANCELLED,null);
        }else {
            if(id == R.id.btnOK){
                setResult(OK,null);
            }
        }
    }

    @Override
    public String uniqueFIntentTag() {
        return FINTENT_TAG;
    }
}
