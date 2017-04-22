package in.ponshere.fintent.sample.fragments.animations;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.FIntentFragment;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.databinding.BindingUS5FragmentA;
import in.ponshere.fintent.sample.fragments.basic.US1FragmentB;
import in.ponshere.fintent.sample.fragments.navigate_to.FIntentNames;

/**
 * @author Ponsuyambu
 * @since 12/4/17.
 */

public class US5FragmentA extends FIntentFragment<BindingUS5FragmentA> implements View.OnClickListener, FIntentNames {


    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnSlideLR.setOnClickListener(this);
        binding.btnSlideUD.setOnClickListener(this);
        binding.btnNoAnimation.setOnClickListener(this);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Animations");
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us5_fragment_a;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnSlideLR) {
            startFragment(new FIntent(US1FragmentB.class, US5FragmentA)); //Default animation
        } else if (id == R.id.btnSlideUD) {
            startFragment(new FIntent(US1FragmentB.class, US5FragmentA).setAnimationType(FIntent.AnimationType.SLIDE_UP_DOWN));
        } else if (id == R.id.btnNoAnimation) {
            startFragment(new FIntent(US1FragmentB.class, US5FragmentA).setAnimationType(FIntent.AnimationType.NONE));
        }

    }
}
