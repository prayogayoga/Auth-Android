package isfaaghyth.app.authlearning.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.isfaaghyth.rak.Rak;
import isfaaghyth.app.authlearning.R;

/**
 * Created by isfaaghyth on 12/31/17.
 * github: @isfaaghyth
 */

public class LecturerActivity extends AppCompatActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lecturer);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_logout)
    public void onLogoutClicked() {
        Rak.entry("login", false);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
