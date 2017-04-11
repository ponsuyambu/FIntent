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
        getFragmentManager().popBackStackImmediate(getFragmentManager().getBackStackEntryAt(0).getId(),FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public int getContainerId() {
        return containerId;
    }


    @Override
    public void navigateTo(String tagName) {
        getFragmentManager().popBackStackImmediate(tagName,FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().findFragmentById(containerId) instanceof IFIntentFragment){
            IFIntentFragment fragment = (IFIntentFragment) getFragmentManager().findFragmentById(containerId);
            if(!fragment.onBackPressed()){
                if(getFragmentManager().getBackStackEntryCount()==1){ //only if 1 item is there, simply finish the activity.
                    if(isAttachedWithActivity){
                        containerActivityRef.get().finish();
                    }else{
                        //TODO: Handle for fragment
                    }
                }else{
                    ((IFIntentActivity)containerActivityRef.get()).callSuperBackPressed();
                }

            }
        }else {
            ((IFIntentActivity)containerActivityRef.get()).callSuperBackPressed();
        }
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
        int enterAnimation = 0;
        int exitAnimation = 0;
        int popEnterAnimation = 0;
        int popExitAnimation = 0;

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
        if(fIntent.hasClearHistoryFlag()){
            if(true){
                //Why we are committing new fragment >> to solve fragment animation issue.
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                final Fragment frg = new Fragment();
                ft.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation);
                ft.replace(containerId, frg);
//              ft.addToBackStack(null);
                ft.commit();
            }

            clearBackStack();
        }
        //!IMPORTANT : if we are not adding item to backstack, that item can't be removed by pop back stack;
//        if(fragmentManager.getFragments() != null && fragmentManager.getFragments().size() > 0){
//            fragmentTransaction = fragmentTransaction.addToBackStack(fIntent.getTag());
//        }
        if(fragmentManager.getBackStackEntryCount() == 0 && !fIntent.hasClearHistoryFlag()){ //For the first transaction, it is weired to show the animation.
            fragmentTransaction.setCustomAnimations(0,0,0,0);
        }
        fragmentTransaction = fragmentTransaction.addToBackStack(fIntent.getTag());
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
