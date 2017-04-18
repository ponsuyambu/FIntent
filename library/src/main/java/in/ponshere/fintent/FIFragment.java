package in.ponshere.fintent;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public abstract class FIFragment<T extends ViewDataBinding> extends Fragment implements IFIntentFragment,FIntentController.Result{

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

    public FIntentController getFIntentController(){
        return FIFactory.getInstance().getController((FIntentControllable) getActivity());
    }


    public int startFragment(FIntent fIntent){
        return getFIntentController().startFragment(updateTransactionNameAndFNameIfNeeded(fIntent));
    }

    public int startFragmentForResult(FIntent fIntent, int requestCode){
        return getFIntentController().startFragmentForResult(updateTransactionNameAndFNameIfNeeded(fIntent),this,requestCode);
    }

    private FIntent updateTransactionNameAndFNameIfNeeded(FIntent fIntent){
        if(fIntent.getCurrentFragmentName() == null){
            fIntent = fIntent.setCurrentFragmentName(uniqueFragmentName());
        }
        return fIntent;
    }

    public boolean navigateTo(String tagName){
        return getFIntentController().navigateTo(tagName);
    }

    public void finish(){
        getFIntentController().finish();
    }

    public void setResult(int resultCode, Bundle bundle){
        getFIntentController().setResult(getTargetFragment(), getTargetRequestCode(),resultCode, bundle);
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
     * Returns the extras bundle sent to this screen.
     * @return the extra data bundle
     */
    public @NonNull Bundle getExtras(){
        return getArguments() == null ? new Bundle() : getArguments() ;
    }

    /**
     * Is fragment handled the back pressed.
     * @return
     */
    public boolean onBackPressed(){
        if(getTargetFragment() != null){
            setResult(CANCELLED,null);
            return true;
        }
        return false;
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {

    }

    @Override
    public String uniqueFragmentName() {
        return getClass().getSimpleName();
    }

}
