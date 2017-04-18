package in.ponshere.fintent;

/**
 * An activity which need to use FIntentController should implement this interface.
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public interface IFIntentActivity extends FIntentControllable{
    void callSuperBackPressed();
}
