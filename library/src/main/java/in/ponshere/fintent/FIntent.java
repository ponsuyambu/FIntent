package in.ponshere.fintent;

import android.os.Bundle;
import android.support.annotation.AnimatorRes;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static in.ponshere.fintent.FIntent.FLAGS.CLEAR_HISTORY;
import static in.ponshere.fintent.FIntent.FLAGS.N0_HISTORY;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public class FIntent {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ N0_HISTORY, CLEAR_HISTORY })
    public @interface FLAGS{
        int N0_HISTORY = 101;
        int CLEAR_HISTORY = 102;
    }


    private @AnimatorRes int enterAnimation = 0;
    private @AnimatorRes int exitAnimation = 0;
    private @AnimatorRes int popEnterAnimation = 0;
    private @AnimatorRes int popExitAnimation = 0;

    private List<Integer> flags = new ArrayList<>();

    private String tag; //This tag is used for fragment backstack & fragment manager

    private Class<? extends Fragment> clazz;
    private Bundle arguments;

    private boolean isAnimate = true;

    public FIntent(Class<? extends Fragment> clazz) {
        this.clazz = clazz;
    }

    public FIntent(Class<? extends Fragment> clazz, Bundle arguments) {
        this.clazz = clazz;
        this.arguments = arguments;
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

    boolean hasNoHistoryFlag(){
        return flags.contains(N0_HISTORY);
    }

    public int getEnterAnimation() {
        return enterAnimation;
    }

    public FIntent setEnterAnimation(int enterAnimation) {
        this.enterAnimation = enterAnimation;
        return this;
    }

    public int getExitAnimation() {
        return exitAnimation;
    }

    public FIntent setExitAnimation(int exitAnimation) {
        this.exitAnimation = exitAnimation;
        return this;
    }

    public int getPopEnterAnimation() {
        return popEnterAnimation;
    }

    public FIntent setPopEnterAnimation(int popEnterAnimation) {
        this.popEnterAnimation = popEnterAnimation;
        return this;
    }

    public int getPopExitAnimation() {
        return popExitAnimation;
    }

    public FIntent setPopExitAnimation(int popExitAnimation) {
        this.popExitAnimation = popExitAnimation;
        return this;
    }

    boolean isAnimate() {
        return isAnimate;
    }

    public FIntent setAnimate(boolean animate) {
        isAnimate = animate;
        return this;
    }

    public String getTag() {
        return tag == null ? clazz.getName() : tag;
    }

    public FIntent setTag(String tag) {
        this.tag = tag;
        return this;
    }
}
