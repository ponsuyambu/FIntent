package in.ponshere.fintent;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public interface FIntentController {

    void setContainerId(@IdRes int containerId);
    @IdRes int getContainerId();

    int startFragment(FIntent fIntent);

    int startFragmentForResult(IFIntentFragment callerFragment, FIntent fIntent);

//    void clearBackStack();-

    void navigateTo(String tagName);

    void onBackPressed();

    void finish();

    void setResult(Fragment targetFragment, int resultCode, Bundle bundle);
}
