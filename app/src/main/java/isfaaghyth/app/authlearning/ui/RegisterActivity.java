package isfaaghyth.app.authlearning.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import isfaaghyth.app.authlearning.R;
import isfaaghyth.app.authlearning.model.Register;
import isfaaghyth.app.authlearning.network.NetworkClient;
import isfaaghyth.app.authlearning.network.Routes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by isfaaghyth on 12/31/17.
 * github: @isfaaghyth
 */

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edt_name) EditText edtName;
    @BindView(R.id.edt_email) EditText edtEmail;
    @BindView(R.id.edt_password) EditText edtRetryPassword;
    @BindView(R.id.edt_retry_password) EditText edtPassword;
    @BindView(R.id.spinner_role) Spinner spinnerRole;

    private Routes routes;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        routes = NetworkClient.client().create(Routes.class);
    }

    @OnClick(R.id.btn_login)
    public void btnLoginClicked() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @OnClick(R.id.btn_register)
    public void btnRegisterClicked() {
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String pass = edtPassword.getText().toString().trim();
        String repass = edtRetryPassword.getText().toString().trim();
        String role = spinnerRole.getSelectedItem().toString().split("\\.")[0];

        if (repass.equals(pass)) {
            onRegister(name, email, pass, role);
        } else {
            Toast.makeText(this, "maaf, password anda tidak sesuai", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Untuk register
     * @param name
     * @param email
     * @param password
     * @param role
     */
    private void onRegister(String name, String email, String password, String role) {
        routes.register(name, email, password, role).enqueue(new Callback<Register>() {
            @Override public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Terjadi kesalahan", Toast.LENGTH_LONG).show();
            }
        });
    }

}
