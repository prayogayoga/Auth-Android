package isfaaghyth.app.authlearning;

import android.app.Application;

import io.isfaaghyth.rak.Rak;

/**
 * Created by isfaaghyth on 12/31/17.
 * github: @isfaaghyth
 */

public class AuthApp extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Rak.initialize(this);
    }
}
