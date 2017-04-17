package in.ponshere.fintent;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AnimRes;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static in.ponshere.fintent.FIntent.AnimationType.NONE;
import static in.ponshere.fintent.FIntent.AnimationType.SLIDE_LEFT_RIGHT;
import static in.ponshere.fintent.FIntent.AnimationType.SLIDE_OVER_TOP;
import static in.ponshere.fintent.FIntent.AnimationType.SLIDE_UP;
import static in.ponshere.fintent.FIntent.AnimationType.SLIDE_UP_DOWN;
import static in.ponshere.fintent.FIntent.FLAGS.CLEAR_HISTORY;

/**
 * An FIntent is an abstract description of an operation to be performed. It can be used with {@link FIFragment#startFragment(FIntent)} to launch a Fragment.
 *
 * Simply, it describes the fragment to be started.
 *
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public class FIntent {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CLEAR_HISTORY })
    public @interface FLAGS{
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

    private static final int ANIMATION_TYPE_IGNORE = -1;

    private int animationType = SLIDE_LEFT_RIGHT;

    private @AnimRes int enterAnimation = 0;
    private @AnimRes int exitAnimation = 0;
    private @AnimRes int popEnterAnimation = 0;
    private @AnimRes int popExitAnimation = 0;

    private List<Integer> flags = new ArrayList<>();

    private String transactionName; // back stack entry name

    private Class<? extends Fragment> clazz;
    private Bundle arguments =  new Bundle();

    private String fragmentNameToLookFor;

    private String currentFragmentName; //fragment manager entry name

    /**
     * Creates the new instance of FIntent
     * @param clazz The component class that is to be used for the intent.
     * @param fIntentName the unique transaction name,
     *                        later this name will be used for fragment pop/navigation.
     */
    public FIntent(Class<? extends Fragment> clazz,String fIntentName) {
        this.clazz = clazz;
        this.transactionName = fIntentName;
        setAnimationType(SLIDE_LEFT_RIGHT);
    }


    /**
     * Creates a FIntent with the fragment(name) to look for. <br>
     * Only when you want to start the already created fragment, you should use this constructor.<br>
     *
     * Usually this fragment name will be given by you for each FIFragment. {@link FIFragment#uniqueFragmentName()}
     *
     * @param fragmentNameToLookFor the fragment name to look for.
     */
    public FIntent(String fragmentNameToLookFor,String fIntentName) {
        this.fragmentNameToLookFor = fragmentNameToLookFor;
        this.transactionName = fIntentName;
        setAnimationType(SLIDE_LEFT_RIGHT);
    }

//    /**
//     * Creates a FIntent with the fragment(name) to look for. <br>
//     * Only when you want to start the already created fragment, you should use this constructor.<br>
//     *
//     * Usually this fragment name will be given you for each FIFragment. {@link FIFragment#uniqueFragmentName()}
//     *
//     * @param fragmentNameToLookFor the fragment name to look for.
//     * @param arguments
//     */
//    public FIntent(String fragmentNameToLookFor, Bundle arguments) {
//        this.arguments = arguments;
//        this.fragmentNameToLookFor = fragmentNameToLookFor;
//        setAnimationType(SLIDE_LEFT_RIGHT);
//    }

    /**
     * Creates the fragments instance associated with this FIntent.
     * @return new instance of fragment.
     */
    @Nullable Fragment createFragment(){
        Fragment instance = null;
        try {
            instance = clazz.newInstance();
            instance.setArguments(arguments);
        } catch (InstantiationException e) {
            //TODO: Error handling
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            //TODO: Error handling
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * Add additional flags to the FIntent
     * @param flag the new flags to set
     * @return returns the same instance, for chaining multiple calls into a single statement.
     */
    public FIntent addFlag(@FLAGS int flag){
        if(!flags.contains(flag))
            flags.add(flag);
        return this;
    }

    /**
     * Sets addtional extras data which has to be passed to the next screen.
     * @param bundle the bundle
     * @return returns the same instance, for chaining multiple calls into a single statement.
     */
    public FIntent setExtras(Bundle bundle){
        this.arguments = bundle;
        return this;
    }

    /**
     * Returns the flags associated with this FIntent
     * @return list of flag valus
     */
    List<Integer> getFlags() {
        return flags;
    }

    String getFragmentNameToLookFor() {
        return fragmentNameToLookFor;
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


    String getTransactionName() {
        return transactionName == null ? clazz.getName() : transactionName;
    }

    String getProvidedTransactionName(){
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

    public FIntent putExtra (String key, Parcelable value){
        arguments.putParcelable(key,value);
        return this;
    }

    public FIntent putExtra (String key, int value){
        arguments.putInt(key,value);
        return this;
    }

    public FIntent putExtra (String key, long[] value){
        arguments.putLongArray(key,value);
        return this;
    }

    public FIntent putExtra (String key, byte value){
        arguments.putByte(key,value);
        return this;
    }

    public FIntent putExtra(String key, double[] value){
        arguments.putDoubleArray(key,value);
        return this;
    }

    public FIntent putExtra(String key, CharSequence value){
        arguments.putCharSequence(key,value);
        return this;
    }

    public FIntent putExtra(String key, boolean[] value){
        arguments.putBooleanArray(key,value);
        return this;
    }

    public FIntent putExtra(String key, char[] value){
        arguments.putCharArray(key,value);
        return this;
    }

    public FIntent putExtra(String key, byte[] value){
        arguments.putByteArray(key,value);
        return this;
    }

    public FIntent putExtra(String key, Parcelable[] value){
        arguments.putParcelableArray(key,value);
        return this;
    }

    public FIntent putExtra(String key, Bundle value){
        arguments.putBundle(key,value);
        return this;
    }

    public FIntent putExtra(String key, CharSequence[] value){
        arguments.putCharSequenceArray(key,value);
        return this;
    }

    public FIntent putExtra(String key, float[] value){
        arguments.putFloatArray(key,value);
        return this;
    }

    public FIntent putExtra(String key, double value){
        arguments.putDouble(key,value);
        return this;
    }

    public FIntent putExtra(String key, int[] value){
        arguments.putIntArray(key,value);
        return this;
    }

    public FIntent putExtra(String key, String[] value){
        arguments.putStringArray(key,value);
        return this;
    }

    public FIntent putExtra(String key, short[] value){
        arguments.putShortArray(key,value);
        return this;
    }

    public FIntent putExtra(String key, boolean value){
        arguments.putBoolean(key,value);
        return this;
    }

    public FIntent putExtra(String key, String value){
        arguments.putString(key,value);
        return this;
    }

    public FIntent putExtra(String key, long value){
        arguments.putLong(key,value);
        return this;
    }

    public FIntent putExtra(String key, char value){
        arguments.putChar(key,value);
        return this;
    }

    public FIntent putExtra(String key, Serializable value){
        arguments.putSerializable(key,value);
        return this;
    }

    public FIntent putExtra(String key, float value){
        arguments.putFloat(key,value);
        return this;
    }

    public FIntent putExtra(String key, short value){
        arguments.putShort(key,value);
        return this;
    }

    public FIntent putExtras(Bundle value){
        arguments.putAll(value);
        return this;
    }

    public FIntent putIntegerArrayListExtra(String key, ArrayList<Integer> value){
        arguments.putIntegerArrayList(key,value);
        return this;
    }

    public FIntent putParcellableArrayListExtra(String key, ArrayList<Parcelable> value){
        arguments.putParcelableArrayList(key,value);
        return this;
    }

    public FIntent putStringArrayListExtra(String key, ArrayList<String> value){
        arguments.putStringArrayList(key,value);
        return this;
    }

}
