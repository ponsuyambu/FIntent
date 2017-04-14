package in.ponshere.fintent;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Main controller class which handles the navigation between fragments
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

    /**
     * Clears the complete back stack
     */
    void clearBackStack(){
        if(getFragmentManager().getBackStackEntryCount() >0){
            getFragmentManager().popBackStackImmediate(getFragmentManager().getBackStackEntryAt(0).getId(),FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public int getContainerId() {
        return containerId;
    }


    /**
     * Navigates to the previous FIntent state.
     * @param fIntentName the name of the FIntent {@link FIntent#FIntent(Class, String)}
     * @return
     */
    @Override
    public boolean navigateTo(String fIntentName) {
        return getFragmentManager().popBackStackImmediate(fIntentName,FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
    public void finish() {
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void setResult(Fragment targetFragment, int requestCode, int resultCode, Bundle bundle) {
        finish();
        IFIntentFragment fIntentFragment = (IFIntentFragment) targetFragment;
        fIntentFragment.onFragmentResult(requestCode, resultCode, bundle);
    }

    @Override
    public int startFragment(FIntent fIntent) {
        return startFragmentForResult(fIntent, null, -1 );
    }

    @Override
    public int startFragmentForResult(FIntent fIntent, IFIntentFragment target, int requestCode) {
        FragmentTransaction fragmentTransaction = null;
        int uniqueTransactionId = -1;
        if(isAttachedWithActivity){
            fragmentTransaction = createFragmentTransaction(fIntent,directParentActivityRef.get().getSupportFragmentManager(),target, requestCode);
        }else if(isAttachedWithFragment){
            fragmentTransaction = createFragmentTransaction(fIntent,directFragmentRef.get().getChildFragmentManager(),target, requestCode);
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

    private FragmentTransaction createFragmentTransaction(FIntent fIntent, FragmentManager fragmentManager,IFIntentFragment target, int requestCode){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction = fragmentTransaction.setCustomAnimations(fIntent.getEnterAnimation(), fIntent.getExitAnimation(),
                fIntent.getPopEnterAnimation(), fIntent.getPopExitAnimation());

        if(fIntent.hasClearHistoryFlag()){
            if(fIntent.hasFragmentNameToLookFor()){
                Log.w(TAG,"Clear history is not supported with fragment name. Silently ignoring the flag.");
            }else{
                //Why we are committing new empty fragment >> to solve fragment animation issue.
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                final Fragment frg = new Fragment();
                ft.setCustomAnimations(fIntent.getEnterAnimation(), fIntent.getExitAnimation(),
                        fIntent.getPopEnterAnimation(), fIntent.getPopExitAnimation());
                ft.replace(containerId, frg);
                ft.commit();
                clearBackStack();
            }
        }
//        if(fIntent.getAnimationType() == FIntent.AnimationType.SLIDE_OVER_TOP){
//            final FragmentTransaction ft = getFragmentManager().beginTransaction();
//            final Fragment frg = new TransparentBackgroundFragment();
////            ft.setCustomAnimations(fIntent.getEnterAnimation(), fIntent.getExitAnimation(),
////                    fIntent.getPopEnterAnimation(), fIntent.getPopExitAnimation());
//            ft.add(containerId, frg);
//            ft.commit();
//        }
        //!IMPORTANT : if we are not adding item to backstack, that item can't be removed by pop back stack;
//        if(fragmentManager.getFragments() != null && fragmentManager.getFragments().size() > 0){
//            fragmentTransaction = fragmentTransaction.addToBackStack(fIntent.getTransactionName());
//        }
        if(fragmentManager.getBackStackEntryCount() == 0 && !fIntent.hasClearHistoryFlag()){ //For the first transaction, it is weired to show the animation.
            fragmentTransaction.setCustomAnimations(0,0,0,0);
        }
        fragmentTransaction = fragmentTransaction.addToBackStack(fIntent.getTransactionName());
        Fragment fragmentToCommit;
        if(fIntent.getFragmentNameToLookFor() != null){
            fragmentToCommit = getFragmentManager().findFragmentByTag(fIntent.getFragmentNameToLookFor());
        }else {
            fragmentToCommit = fIntent.createFragment();
        }
        if(target != null){
            fragmentToCommit.setTargetFragment((Fragment) target,requestCode);
        }


            fragmentTransaction.replace(containerId,fragmentToCommit, ((IFIntentFragment)fragmentToCommit).uniqueFragmentName());

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
