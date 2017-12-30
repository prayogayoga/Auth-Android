package isfaaghyth.app.authlearning.network;

import isfaaghyth.app.authlearning.model.Login;
import isfaaghyth.app.authlearning.model.Register;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by isfaaghyth on 12/31/17.
 * github: @isfaaghyth
 */

public interface Routes {

    /**
     * ini untuk register user
     * @param name
     * @param email
     * @param password
     * @param role
     * @return
     */
    @FormUrlEncoded @POST("users/register")
    Call<Register> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("role") String role
    );

    /**
     * Ini untuk login
     * @param email
     * @param password
     * @return
     */
    @FormUrlEncoded @POST("users/login")
    Call<Login> login(
            @Field("email") String email,
            @Field("password") String password
    );

}
