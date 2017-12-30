package isfaaghyth.app.authlearning.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.isfaaghyth.rak.Rak;
import isfaaghyth.app.authlearning.R;
import isfaaghyth.app.authlearning.model.Login;
import isfaaghyth.app.authlearning.network.NetworkClient;
import isfaaghyth.app.authlearning.network.Routes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by isfaaghyth on 12/31/17.
 * github: @isfaaghyth
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edt_email) EditText edtEmail;
    @BindView(R.id.edt_password) EditText edtPassword;

    private Routes routes;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        routes = NetworkClient.client().create(Routes.class);

        if (Rak.isExist("login")) {
            if (Rak.grab("login")) {
                int role = Rak.grab("role");
                if (role == 1) {
                    startActivity(new Intent(LoginActivity.this, LecturerActivity.class));
                    finish();
                } else if (role == 2) {
                    startActivity(new Intent(LoginActivity.this, StudentsActivity.class));
                    finish();
                }
            }
        }
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String email = edtEmail.getText().toString().trim();
                String pass = edtPassword.getText().toString().trim();
                onLogin(email, pass);
                break;
            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
        }
    }

    private void onLogin(String email, String password) {
        routes.login(email, password).enqueue(new Callback<Login>() {
            @Override public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.code() == 200) {
                    int role = response.body().getUser().getRole();
                    if (role == 1) {
                        startActivity(new Intent(LoginActivity.this, LecturerActivity.class));
                        finish();
                    } else if (role == 2) {
                        startActivity(new Intent(LoginActivity.this, StudentsActivity.class));
                        finish();
                    }
                    Rak.entry("login", true);
                    Rak.entry("role", role);
                } else if (response.code() == 401) {
                    Toast.makeText(LoginActivity.this, "email/pass anda salah", Toast.LENGTH_LONG).show();
                }
            }

            @Override public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Terjadi kesalahan", Toast.LENGTH_LONG).show();
            }
        });
    }

}
