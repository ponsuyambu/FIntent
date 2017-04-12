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
     * Unique tag which is used for FIntent transactions.
     * This tag name will be helpful to navigate to the particular position in the navigation.
     * @return the fragment should return the unique value which identifies the screen.
     */
    String uniqueFIntentTag();
}
