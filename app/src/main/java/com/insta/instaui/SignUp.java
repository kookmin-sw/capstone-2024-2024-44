package com.insta.instaui;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.insta.instaui.MainActivity;
import com.insta.instaui.R;

public class SignUp extends Activity {
     FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        mAuth = FirebaseAuth.getInstance();
        ImageView back_sign_in = findViewById(R.id.back_sign_in);
        back_sign_in.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
        Button btn_register = findViewById(R.id.btn_register);
        EditText txt_name = findViewById(R.id.user_name);
        EditText txt_id = findViewById(R.id.user_id);
        EditText txt_password = findViewById(R.id.user_password);
        EditText txt_password_confirm = findViewById(R.id.user_password_confirm);
        EditText txt_email = findViewById(R.id.user_email);

        btn_register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = String.valueOf(txt_email.getText());
                        String password = String.valueOf(txt_password.getText());
                        if (!txt_password.getText().toString().equals( txt_password_confirm.getText().toString())){
                            Toast.makeText(SignUp.this, "The Password Not Equal ", Toast.LENGTH_SHORT).show();
                        }else if(txt_name.getText().toString().equals("")){
                            Toast.makeText(SignUp.this, "You  Must Fill Your Name ", Toast.LENGTH_SHORT).show();

                        }else if(txt_id.getText().toString().equals("")){
                            Toast.makeText(SignUp.this, "You  Must Fill Your ID ", Toast.LENGTH_SHORT).show();

                        }else if(txt_email.getText().toString().equals("")){
                            Toast.makeText(SignUp.this, "You  Must Fill Your Email ", Toast.LENGTH_SHORT).show();

                        }else if(txt_password.getText().toString().equals("")){
                            Toast.makeText(SignUp.this, "You  Must Fill Your Password", Toast.LENGTH_SHORT).show();

                        }else{
                            mAuth.createUserWithEmailAndPassword(email,password)
                                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                // Sign in success, update UI with the signed-in user's information
                                                Log.d("SignUp", "createUserWithEmail:success");

                                                Toast.makeText(SignUp.this, "Successfully Register ^_^  ", Toast.LENGTH_SHORT).show();
                                                finish();
                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Log.w("SignUp", "createUserWithEmail:failure", task.getException());
                                                Toast.makeText(SignUp.this, "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }
                    }
                }
        );
    }
}
