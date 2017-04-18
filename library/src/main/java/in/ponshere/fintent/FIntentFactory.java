package in.ponshere.fintent;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.WeakHashMap;

/**
 * Factory class used to create the FIntent controller instance.
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public class FIntentFactory {

    private static final String TAG = "FIntentFactory";

    private static FIntentFactory instance;
    private WeakHashMap<FIntentControllable, FIntentController> controllerHashMap = new WeakHashMap<>();

    private FIntentFactory(){

    }

    public static FIntentFactory getInstance() {
        if(instance == null){
            instance = new FIntentFactory();
        }
        return instance;
    }

    /**
     * Creates an FIntentController for this activity. If there is a already created controller exists for this activity,
     * this will return the old instance.
     * @param controllable
     * @param containerId
     * @return
     */
    public FIntentController createFIntentController(FIntentControllable controllable, @IdRes int containerId){
        if(!controllerHashMap.containsKey(controllable)){
            Log.d(TAG,"Creating new instance of controller for "+controllable);
            FIntentController controller = new FIntentControllerImpl(controllable);
            controller.setContainerId(containerId);
            controllerHashMap.put(controllable, controller);
        }
        return controllerHashMap.get(controllable);
    }

    /**
     * Gets the controller associated with this activity/fragment.
     * @param controllable the controllable instance, normally an activity or fragment.
     * @return the controller instance
     */
    @Nullable FIntentController getController(FIntentControllable controllable) {
        return controllerHashMap.get(controllable);
    }
}
