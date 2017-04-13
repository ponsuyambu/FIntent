package in.ponshere.fintent;

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static in.ponshere.fintent.FIntent.AnimationType.NONE;
import static in.ponshere.fintent.FIntent.AnimationType.SLIDE_OVER_TOP;
import static in.ponshere.fintent.FIntent.AnimationType.SLIDE_LEFT_RIGHT;
import static in.ponshere.fintent.FIntent.AnimationType.SLIDE_UP;
import static in.ponshere.fintent.FIntent.AnimationType.SLIDE_UP_DOWN;
import static in.ponshere.fintent.FIntent.FLAGS.CLEAR_HISTORY;
import static in.ponshere.fintent.FIntent.FLAGS.N0_HISTORY;

/**
 * FIntent class which describes the fragment to be started.
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public class FIntent {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ N0_HISTORY, CLEAR_HISTORY })
    public @interface FLAGS{
        @Deprecated
        int N0_HISTORY = 101;
        int CLEAR_HISTORY = 102;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ NONE, SLIDE_LEFT_RIGHT,SLIDE_UP,SLIDE_UP_DOWN, SLIDE_OVER_TOP})
    public @interface AnimationType{
        int NONE = 200;
        int SLIDE_LEFT_RIGHT = 201;
        int SLIDE_UP = 202;
        int SLIDE_UP_DOWN = 203;
        int SLIDE_OVER_TOP = 204;
    }

    static final int ANIMATION_TYPE_IGNORE = -1;

    private int animationType = SLIDE_LEFT_RIGHT;

    private @AnimRes int enterAnimation = 0;
    private @AnimRes int exitAnimation = 0;
    private @AnimRes int popEnterAnimation = 0;
    private @AnimRes int popExitAnimation = 0;

    private int[] animations = null;

    private List<Integer> flags = new ArrayList<>();

    private String transactionName; //This transactionName is used for fragment backstack & fragment manager

    private Class<? extends Fragment> clazz;
    private Bundle arguments;

    private String fragmentNameToLookFor;

    private String currentFragmentName;

    public FIntent(Class<? extends Fragment> clazz) {
        this.clazz = clazz;
        setAnimationType(SLIDE_LEFT_RIGHT);
    }

    public FIntent(Class<? extends Fragment> clazz, Bundle arguments) {
        this.clazz = clazz;
        this.arguments = arguments;
        setAnimationType(SLIDE_LEFT_RIGHT);
    }

    public FIntent(String fragmentNameToLookFor) {
        this.fragmentNameToLookFor = fragmentNameToLookFor;
        setAnimationType(SLIDE_LEFT_RIGHT);
    }

    public FIntent(String fragmentNameToLookFor, Bundle arguments) {
        this.arguments = arguments;
        this.fragmentNameToLookFor = fragmentNameToLookFor;
        setAnimationType(SLIDE_LEFT_RIGHT);
    }

    @Nullable Fragment getFragment(){
        Fragment instance = null;
        try {
            instance = clazz.newInstance();
            instance.setArguments(arguments);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public FIntent addFlag(@FLAGS int flag){
        if(!flags.contains(flag))
            flags.add(flag);
        return this;
    }

    public FIntent setArguments(Bundle bundle){
        this.arguments = bundle;
        return this;
    }

    List<Integer> getFlags() {
        return flags;
    }

    public String getFragmentNameToLookFor() {
        return fragmentNameToLookFor;
    }

    @Deprecated
    boolean hasNoHistoryFlag(){
        return flags.contains(N0_HISTORY);
    }

    boolean hasClearHistoryFlag(){
        return flags.contains(CLEAR_HISTORY);
    }

    public int getEnterAnimation() {
        return enterAnimation;
    }

    public int getExitAnimation() {
        return exitAnimation;
    }


    public int getPopEnterAnimation() {
        return popEnterAnimation;
    }


    public int getPopExitAnimation() {
        return popExitAnimation;
    }


    public String getTransactionName() {
        return transactionName == null ? clazz.getName() : transactionName;
    }

    String getOriginalTag(){
        return transactionName;
    }

    public FIntent setTransactionName(String transactionName) {
        this.transactionName = transactionName;
        return this;
    }

    String getCurrentFragmentName() {
        return currentFragmentName;
    }

    public FIntent setCurrentFragmentName(String currentFragmentName) {
        this.currentFragmentName = currentFragmentName;
        return this;
    }

    boolean hasFragmentClass(){
        return clazz != null;
    }

    boolean hasFragmentNameToLookFor(){
        return fragmentNameToLookFor != null;
    }

    int getAnimationType() {
        return animationType;
    }

    public FIntent setAnimationType(@AnimationType int animationType) {
        this.animationType = animationType;
        if(animationType == NONE){
            enterAnimation = 0;
            exitAnimation = 0;
            popEnterAnimation = 0;
            popExitAnimation = 0;
        } else if(animationType == SLIDE_LEFT_RIGHT){
            enterAnimation = R.anim.right_to_left_in;
            exitAnimation = R.anim.right_to_left_exit;
            popEnterAnimation = R.anim.left_to_right_in;
            popExitAnimation = R.anim.left_to_right_exit;
        } else if(animationType == SLIDE_UP_DOWN){
            enterAnimation = R.anim.slide_in_up;
            exitAnimation = R.anim.slide_out_up;
            popEnterAnimation = R.anim.slide_in_down;
            popExitAnimation = R.anim.slide_out_down;
        } else if(animationType == SLIDE_OVER_TOP){
            enterAnimation = R.anim.slide_in_up;
            exitAnimation = 0;
            popEnterAnimation = R.anim.hold;
            popExitAnimation = R.anim.slide_out_down;
        }
        return this;
    }

    public FIntent setCustomAnimations(@AnimRes int enterAnimation, @AnimRes int exitAnimation, @AnimRes int popEnterAnimation, @AnimRes int popExitAnimation) {
        this.enterAnimation = enterAnimation;
        this.exitAnimation = exitAnimation;
        this.popEnterAnimation = popEnterAnimation;
        this.popExitAnimation = popExitAnimation;
        this.animationType = ANIMATION_TYPE_IGNORE;
        return this;

    }
}
