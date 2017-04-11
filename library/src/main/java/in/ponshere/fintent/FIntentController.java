package in.ponshere.fintent;

import android.support.annotation.IdRes;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public interface FIntentController {

    void setContainerId(@IdRes int containerId);
    @IdRes int getContainerId();

    int startFragment(FIntent fIntent);

    void clearBackStack();

    void navigateTo(String tagName);
}
