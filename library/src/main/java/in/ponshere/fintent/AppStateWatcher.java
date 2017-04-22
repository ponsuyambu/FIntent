package in.ponshere.fintent;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;


/**
 * Watcher class which tracks the activity's lifecycle. This class is used to store the pending transactions
 * when the application is in background.
 *
 * @author Ponsuyambu
 * @since 11/4/17.
 */

class AppStateWatcher implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = "AppStateWatcher";

    private boolean isAppVisible = false;
    private ArrayList<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        } else {
            Log.w(TAG, "Listener already exists");
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        isAppVisible = true;
        for (Listener listener : listeners) {
            listener.onAppResumed(activity);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        isAppVisible = false;
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public boolean isAppVisible() {
        return isAppVisible;
    }

    interface Listener {
        void onAppResumed(Activity activity);
    }
}
