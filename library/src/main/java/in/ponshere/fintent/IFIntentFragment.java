package in.ponshere.fintent;

import android.os.Bundle;

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

    void onFragmentResult(int resultCode, Bundle data);
}
