package in.ponshere.fintent;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public interface IFIntentFragment extends FIntentControllable {
    /**
     * Triggered on activity back press.
     * @return
     */
    boolean onBackPressed();
}
