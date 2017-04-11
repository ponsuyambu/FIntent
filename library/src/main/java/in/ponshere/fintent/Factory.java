package in.ponshere.fintent;

import android.util.Log;

import java.util.WeakHashMap;

/**
 * @author Ponsuyambu
 * @since 11/4/17.
 */

public class Factory {

    private static final String TAG = "FIntentFactory";

    private static Factory instance;
    private WeakHashMap<FIntentControllable, FIntentController> controllerHashMap = new WeakHashMap<>();

    private Factory(){

    }

    public static Factory getInstance() {
        if(instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public FIntentController getController(FIntentControllable controllable){
        if(!controllerHashMap.containsKey(controllable)){
            Log.d(TAG,"Creating new instance of controller for "+controllable);
            controllerHashMap.put(controllable,new FIntentControllerImpl(controllable));
        }
        return controllerHashMap.get(controllable);
    }
}
