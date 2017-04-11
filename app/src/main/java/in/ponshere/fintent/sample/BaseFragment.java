package in.ponshere.fintent.sample;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.ponshere.fintent.IFIntentFragment;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment implements IFIntentFragment{

    private static final String TAG_LIFE_CYCLE = "LifeCycle";

    protected T binding;
    private boolean isFirstTimeViewCreated;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG_LIFE_CYCLE,"onCreate - "+this);
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG_LIFE_CYCLE,"onCreateView - "+this);
        binding = DataBindingUtil.inflate(inflater,getLayoutResourceId(),container,false);
        return binding.getRoot();
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(!isFirstTimeViewCreated){
            onFirstTimeViewCreated(savedInstanceState);
            isFirstTimeViewCreated = true;
        }
        onViewCreated(savedInstanceState);
    }

    /**
     * When the first time fragment view is created is method will be invoked.
     * This is the ideal place to call the initial web service, setting adapter etc.
     * <br>
     * <br>
     * To get the view instances, you can use the 'binding' variable, or use getView method directly.
     *
     * @param savedInstanceState old saved state of the fragment
     */
    protected void onFirstTimeViewCreated(@Nullable Bundle savedInstanceState){

    }

    /**
     * Wrapper callback for fragment's onViewCreated method.
     *
     * <br>
     * <br>
     * To get the view instances, you can use the 'binding' variable, or use getView method directly.
     * @param savedInstanceState old saved state of the fragment
     */
    protected void onViewCreated(@Nullable Bundle savedInstanceState){

    }

    public abstract @LayoutRes int getLayoutResourceId();


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG_LIFE_CYCLE,"onResume - "+this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG_LIFE_CYCLE,"onStart - "+this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG_LIFE_CYCLE,"onStop - "+this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG_LIFE_CYCLE,"onAttach - "+this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG_LIFE_CYCLE,"onDetach - "+this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LIFE_CYCLE,"onDestroy - "+this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG_LIFE_CYCLE,"onDestroyView - "+this);
    }

    /**
     * Is fragment handled the back pressed.
     * @return
     */
    public boolean onBackPressed(){
        return false;
    }
}
