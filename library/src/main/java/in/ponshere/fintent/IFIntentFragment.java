package in.ponshere.fintent;

import android.os.Bundle;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public interface IFIntentFragment extends FIntentControllable {
    /**
     * Triggered on activity back press.
     * @return child fragment returns true if it consumes the back press
     */
    boolean onBackPressed();

    void onFragmentResult(int requestCode, int resultCode, Bundle data);


    /**
     * Unique name for the fragment, this will be helpful to bring the fragment with the old state.
     * @return an unique name for the fragment
     */
    String uniqueFragmentName();
}
