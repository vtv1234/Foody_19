package hcmute.edu.vn.foody_18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.foody_18.Database.DatabaseHandler;
import hcmute.edu.vn.foody_18.Fragment.AccountFragment;
import hcmute.edu.vn.foody_18.Model.User;

public class SignInActivity extends AppCompatActivity {

    Button buttonLogin;
    EditText editEmail, editPass;
    TextView textRegister;
    DatabaseHandler db;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sharedPreferences = getSharedPreferences("currentUser", MODE_PRIVATE);

        mapping();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String pass = editPass.getText().toString().trim();

                if (email.equals("") || pass.equals("")) {
                    Toast.makeText(SignInActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean login = db.loginUser(email, pass);
                    if (login) {
                        Toast.makeText(SignInActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                        User user = db.getInfoUser(email);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt(    "userId",   user.getUserId());
                        editor.putString( "userName", user.getUserName());
                        editor.putString( "email",    user.getEmail());
                        editor.putString( "address",  user.getAddress());
                        editor.putString( "phone",    user.getPhone());
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), AccountFragment.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignInActivity.this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void mapping() {
        buttonLogin     = findViewById(R.id.signInActivity_button_login);
        editEmail       = findViewById(R.id.signInActivity_edittext_email);
        editPass        = findViewById(R.id.signInActivity_edittext_password);
        textRegister    = findViewById(R.id.signInActivity_textview_register);
        db = DatabaseHandler.getInstance(this);
    }
}