package isfaaghyth.app.authlearning.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by isfaaghyth on 12/31/17.
 * github: @isfaaghyth
 */

public class NetworkClient {

    public static Retrofit client() {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.43.98:1337/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
