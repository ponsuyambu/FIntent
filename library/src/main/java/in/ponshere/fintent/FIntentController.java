package in.ponshere.fintent;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

/**
 * Main controller interface for the fragment transaction
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public interface FIntentController {

    interface Result{
        int OK = 11;
        int CANCELLED = 12;
    }


    /**
     * Sets the container id for this controller
     * @param containerId the container id
     */
    void setContainerId(@IdRes int containerId);

    /**
     * Returns the container id associated with this controller
     * @return
     */
    @IdRes int getContainerId();

    /**
     * Starts the fragment
     * @param fIntent the fintent instance
     * @return unique transaction id
     */
    int startFragment(FIntent fIntent);

    /**
     * Starts the fragment for result
     * @param fIntent the fintent instance
     * @param callerFragment caller fragment which expects the result
     * @param requestCode the unique request code
     * @return unique transaction id
     */
    int startFragmentForResult(FIntent fIntent, IFIntentFragment callerFragment, int requestCode);

    /**
     * Navigates to the fragment mapped with the tag
     * @param tagName the tag name
     */
    boolean navigateTo(String tagName);

    /**
     * This method has to be invoked by the activity associated with
     * the controller.
     */
    void onBackPressed();


    /**
     * Finishes/pops/exits the current fragment
     */
    void finish();

    /**
     * Sets the result to the fragment which invokes the startFragmentForResult
     * @param targetFragment the source fragment which invokes the startFragmentForResult
     * @param requestCode the request code
     * @param resultCode the result code
     * @param bundle the data bundle
     */
    void setResult(Fragment targetFragment, int requestCode, int resultCode, Bundle bundle);
}
