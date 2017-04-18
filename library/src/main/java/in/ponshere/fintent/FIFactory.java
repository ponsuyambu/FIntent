package in.ponshere.fintent;

import android.support.annotation.IdRes;
import android.util.Log;

import java.util.WeakHashMap;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public class FIFactory {

    private static final String TAG = "FIntentFactory";

    private static FIFactory instance;
    private WeakHashMap<FIntentControllable, FIntentController> controllerHashMap = new WeakHashMap<>();

    private FIFactory(){

    }

    public static FIFactory getInstance() {
        if(instance == null){
            instance = new FIFactory();
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

    FIntentController getController(FIntentControllable controllable) {
        return controllerHashMap.get(controllable);
    }
}
