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
 * The base class for the fragment. All your fragment classes should extend this class.
 *
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public abstract class FIntentFragment<T extends ViewDataBinding> extends Fragment implements IFIntentFragment, FIntentController.Result {

    private static final String TAG_LIFE_CYCLE = "LifeCycle";

    /**
     * The View binding instance, which helps to find the views associated with this fragment.
     * This binding object, eliminates the multiple calls of findViewById.
     */
    protected T binding;
    private boolean isFirstTimeViewCreated;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        Log.d(TAG_LIFE_CYCLE, "onCreate - " + this);
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG_LIFE_CYCLE, "onCreateView - " + this);
        binding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false);
        return binding.getRoot();
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isFirstTimeViewCreated) {
            onFirstTimeViewCreated(savedInstanceState);
            isFirstTimeViewCreated = true;
        }
        onViewCreated(savedInstanceState);
    }

    /**
     * Returns the FIntentController associated with its parent.
     *
     * @return the controller instance
     */
    public FIntentController getFIntentController() {
        return FIntentFactory.getInstance().getController((FIntentControllable) getActivity());
    }


    /**
     * Starts the fragment (in parent's FIntentController)
     *
     * @param fIntent the information about the next fragment to be started
     * @return the unique id which denotes this particular transaction.
     */
    public int startFragment(FIntent fIntent) {
        return getFIntentController().startFragment(updateTransactionNameAndFNameIfNeeded(fIntent));
    }

    /**
     * Starts the fragment for getting the result.
     *
     * @param fIntent     the information about the next fragment to be started.
     * @param requestCode the unique request code which helps to get back the results.
     * @return the unique id which denotes this particular transaction.
     */
    public int startFragmentForResult(FIntent fIntent, int requestCode) {
        return getFIntentController().startFragmentForResult(updateTransactionNameAndFNameIfNeeded(fIntent), this, requestCode);
    }

    private FIntent updateTransactionNameAndFNameIfNeeded(FIntent fIntent) {
        if (fIntent.getCurrentFragmentName() == null) {
            fIntent = fIntent.setCurrentFragmentName(uniqueFragmentName());
        }
        return fIntent;
    }

    /**
     * Navigates to the particular FIntent state.
     *
     * @param fIntentName the name of the FIntent. {@link FIntent#FIntent(Class, String)}
     * @return true if the navigation is successful.
     */
    public boolean navigateTo(String fIntentName) {
        return getFIntentController().navigateTo(fIntentName);
    }

    /**
     * Finishes/closes the current fragment
     */
    public void finish() {
        getFIntentController().finish();
    }

    /**
     * Sets the result for the fragment which is started by {@link FIntentFragment#startFragmentForResult(FIntent, int)}
     *
     * @param resultCode the result code {@link in.ponshere.fintent.FIntentController.Result}
     * @param bundle     the bundle which carries the additional result data
     */
    public void setResult(int resultCode, Bundle bundle) {
        getFIntentController().setResult(getTargetFragment(), getTargetRequestCode(), resultCode, bundle);
    }


    /**
     * When the first time fragment view is created this method will be invoked.
     * This is the ideal place to call the initial web service, setting adapter etc.
     * <br>
     * <br>
     * To get the view instances, you can use the {@link FIntentFragment#binding} variable, or use getView method directly.
     *
     * @param savedInstanceState old saved state of the fragment
     */
    protected void onFirstTimeViewCreated(@Nullable Bundle savedInstanceState) {

    }

    /**
     * Wrapper callback for fragment's onViewCreated method.
     * <p>
     * <br>
     * <br>
     * To get the view instances, you can use the 'binding' variable, or use getView method directly.
     *
     * @param savedInstanceState old saved state of the fragment
     */
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {

    }

    public abstract @LayoutRes
    int getLayoutResourceId();


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG_LIFE_CYCLE, "onResume - " + this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG_LIFE_CYCLE, "onStart - " + this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG_LIFE_CYCLE, "onStop - " + this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG_LIFE_CYCLE, "onAttach - " + this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG_LIFE_CYCLE, "onDetach - " + this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LIFE_CYCLE, "onDestroy - " + this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG_LIFE_CYCLE, "onDestroyView - " + this);
    }

    /**
     * Returns the extras bundle sent to this screen.
     *
     * @return the extra data bundle
     */
    public @NonNull
    Bundle getExtras() {
        return getArguments() == null ? new Bundle() : getArguments();
    }

    /**
     * Hardware back button pressed callback for the fragment. The fragment exit logic can be handled inside this
     * method. (Ex. Exit pop up)
     *
     * @return return true if you handled it on your own, returning false will pop the fragment, brings back the
     * previous fragment.
     */
    public boolean onBackPressed() {
        if (getTargetFragment() != null) {
            setResult(CANCELLED, null);
            return true;
        }
        return false;
    }

    /**
     * Invoked when the target fragment returns the result to the calling fragment. {@link FIntentFragment#startFragmentForResult(FIntent, int)}
     *
     * @param requestCode the request code
     * @param resultCode  the result code {@link in.ponshere.fintent.FIntentController.Result}
     * @param data        the additional data which describes the result
     */
    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {

    }

    /**
     * The unique fragment name which helps to identify this fragment instance in the fragment manager.
     * Later, this name will be helpful if you want show the same instance of fragment in your flow.
     * {@link FIntent#FIntent(String, String)}
     *
     * @return the unique name. By default the class name of the fragment is used. It is recommended to override this method,
     * when you want to use {@link FIntent#FIntent(String, String)}
     */
    @Override
    public String uniqueFragmentName() {
        return getClass().getSimpleName();
    }

}
