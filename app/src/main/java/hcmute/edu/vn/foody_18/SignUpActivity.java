package hcmute.edu.vn.foody_18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.foody_18.Database.DatabaseHandler;

public class SignUpActivity extends AppCompatActivity {

    Button buttonRegister;
    EditText editEmail, editPass, editRePass;
    TextView textLogin;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mapping();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String pass = editPass.getText().toString().trim();
                String repass = editRePass.getText().toString().trim();

                if (email.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(SignUpActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (pass.equals(repass)) {
                    Boolean check = db.checkExistUser(email);
                    if (!check) {
                        Boolean result = db.addUser(email, pass);
                        if (result) {
                            Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "Email đã được đăng ký\nVui lòng đăng nhập", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this, "Mật khẩu xác nhận không trùng khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    public void mapping() {
        buttonRegister  = findViewById(R.id.signUpActivity_button_register);
        editEmail       = findViewById(R.id.signUpActivity_edittext_Email);
        editPass        = findViewById(R.id.signUpActivity_edittext_password);
        editRePass      = findViewById(R.id.signUpActivity_edittext_confirm_password);
        textLogin       = findViewById(R.id.signUpActivity_textview_login);
        db = DatabaseHandler.getInstance(this);
    }
}