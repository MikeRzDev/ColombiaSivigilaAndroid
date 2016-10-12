package clases;

import android.app.Application;
import android.content.Context;

/**
 * Created by user on 11/10/2016.
 */

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        if (context == null){
            context = getApplicationContext();
        }
    }

    public static Context getContext() {
        return context;
    }
}
