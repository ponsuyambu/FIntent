package in.ponshere.fintent;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public interface FIntentController {

    int RESULT_OK = 11;
    int RESULT_CANCELLED = 12;

    void setContainerId(@IdRes int containerId);
    @IdRes int getContainerId();

    int startFragment(FIntent fIntent);

    int startFragmentForResult(FIntent fIntent, IFIntentFragment callerFragment, int requestCode);

//    void clearBackStack();-

    void navigateTo(String tagName);

    void onBackPressed();

    void finish();

    void setResult(Fragment targetFragment, int requestCode, int resultCode, Bundle bundle);
}
