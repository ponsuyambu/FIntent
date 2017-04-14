package in.ponshere.fintent.sample.fragments.start_for_result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ponshere.fintent.FIFragment;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS6FragmentB;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US6FragmentB extends FIFragment<BindingUS6FragmentB> implements View.OnClickListener{

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
}
