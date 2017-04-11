package in.ponshere.fintent;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

class FIntentControllerImpl implements FIntentController,AppStateWatcher.Listener,FragmentManager.OnBackStackChangedListener{


    private static final String TAG = "FIntentController";

    private static AppStateWatcher appStateWatcher = null;

    private boolean isAttachedWithActivity = false;
    private boolean isAttachedWithFragment = false;

    private WeakReference<FragmentActivity> directParentActivityRef;
    private WeakReference<Fragment> directFragmentRef;
    private WeakReference<FragmentActivity> containerActivityRef;

    private ArrayList<FragmentTransaction> mPendingTransactions = new ArrayList<>();

    private int containerId;

    private int backStackEntry = 0;

    FIntentControllerImpl(FIntentControllable controllable){
        if(controllable instanceof Fragment || controllable instanceof FragmentActivity){
            if(controllable instanceof  Fragment){
                containerActivityRef = new WeakReference<>(((Fragment)controllable).getActivity());
                isAttachedWithFragment = true;
                directFragmentRef = new WeakReference<>(((Fragment)controllable));
            }else{
                isAttachedWithActivity = true;
                directParentActivityRef = new WeakReference<>(((FragmentActivity)controllable));
                containerActivityRef = directParentActivityRef;
            }
            if(appStateWatcher == null){ //if application is restarted, instance will be null and this constructor will be invoked properly.
                appStateWatcher = new AppStateWatcher();
                containerActivityRef.get().getApplication().registerActivityLifecycleCallbacks(appStateWatcher);
            }
            appStateWatcher.addListener(this);
            getFragmentManager().addOnBackStackChangedListener(this);
            backStackEntry = getFragmentManager().getBackStackEntryCount();

        }else{
            throw new IllegalStateException("FIntent controller can only be attached with FragmentActivity or Fragment ");
        }

    }

    public void clearBackStack(){
        getFragmentManager().popBackStackImmediate(""+1,FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        FragmentTransaction transaction;
//        for (Fragment fragment : getFragmentManager().getFragments()){
//            Log.d(TAG,fragment != null ? fragment.toString(): "null");
//            transaction = getFragmentManager().beginTransaction();
//            if(fragment != null){
//                transaction.remove(fragment).commit();
//            }
//        }

    }

    public int getContainerId() {
        return containerId;
    }


    @Override
    public void navigateTo(String tagName) {
        getFragmentManager().popBackStackImmediate(tagName,FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public int startFragment(FIntent fIntent) {
        FragmentTransaction fragmentTransaction = null;
        int uniqueTransactionId = -1;
        if(isAttachedWithActivity){
            fragmentTransaction = createFragmentTransaction(fIntent,directParentActivityRef.get().getSupportFragmentManager());
        }else if(isAttachedWithFragment){
            fragmentTransaction = createFragmentTransaction(fIntent,directFragmentRef.get().getChildFragmentManager());
        }
        if(appStateWatcher.isAppVisible()){
            uniqueTransactionId = fragmentTransaction.commit();
        }else{
            mPendingTransactions.add(fragmentTransaction);
        }
        return uniqueTransactionId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    private FragmentManager getFragmentManager(){
        if(isAttachedWithActivity){
            return directParentActivityRef.get().getSupportFragmentManager();
        }else if(isAttachedWithFragment){
            return directFragmentRef.get().getChildFragmentManager();
        }
        return null;
    }

    private FragmentTransaction createFragmentTransaction(FIntent fIntent, FragmentManager fragmentManager){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        int enterAnimation;
        int exitAnimation;
        int popEnterAnimation;
        int popExitAnimation;

        if (fIntent.isAnimate()) {
            enterAnimation = fIntent.getEnterAnimation() == 0 ? R.anim.right_to_left_in
                    : fIntent.getEnterAnimation();
            exitAnimation = fIntent.getExitAnimation() == 0 ? R.anim.right_to_left_exit
                    : fIntent.getExitAnimation();
            popEnterAnimation = fIntent.getPopEnterAnimation() == 0 ? R.anim.left_to_right_in
                    : fIntent.getPopEnterAnimation();
            popExitAnimation = fIntent.getPopExitAnimation() == 0 ? R.anim.left_to_right_exit
                    : fIntent.getPopExitAnimation();

            fragmentTransaction = fragmentTransaction.setCustomAnimations(enterAnimation, exitAnimation,
                    popEnterAnimation, popExitAnimation);
        }

        if(!fIntent.hasNoHistoryFlag()){
            ++backStackEntry;
        }
        if(fragmentManager.getFragments() != null && fragmentManager.getFragments().size() > 0){
            fragmentTransaction = fragmentTransaction.addToBackStack(fIntent.getTag());
        }

        fragmentTransaction.replace(containerId,fIntent.getFragment());

        return fragmentTransaction;
    }

    @Override
    public void onAppResumed(Activity activity) {
        if(activity.equals(containerActivityRef.get())){
            for(FragmentTransaction transaction : mPendingTransactions){
                transaction.commit();
            }
            mPendingTransactions.clear();//TODO: Concurrent ArrayList can be used

        }
    }

    @Override
    public void onBackStackChanged() {
        backStackEntry = getFragmentManager().getBackStackEntryCount();
        Log.d(TAG,"Back stack entry pointed to = "+backStackEntry);
    }
}
