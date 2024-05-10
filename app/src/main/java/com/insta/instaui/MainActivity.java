package com.insta.instaui;


import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    SharedPreferences sharedPreferences;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("mylogin",MODE_PRIVATE);

        if ( sharedPreferences.getInt("IS_LOGIN",0) == 1){
            Intent HomeIntent = new Intent(this , HomeActivity.class);
            startActivity( HomeIntent);
        }
        setContentView(R.layout.activity_main);
        TextView txt_sign_up = findViewById(R.id.sign_up);

        txt_sign_up.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(v.getContext(), com.insta.instaui.SignUp.class);
                        startActivity( myIntent);
                    }
                }
        );
        Button btn_login = findViewById(R.id.btn_login);
        EditText txt_username = findViewById(R.id.username_input);
        EditText txt_password = findViewById(R.id.password_input);
        mAuth = FirebaseAuth.getInstance();


        btn_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = String.valueOf(txt_username.getText());
                        String password = String.valueOf(txt_password.getText());
                        Log.d("Login", "signInWithEmail:success Email:"+email+" password:"+password);
                        if (email.isEmpty() || password.isEmpty()){
                            Toast.makeText(MainActivity.this, "You must fill email and password", Toast.LENGTH_SHORT).show();

                            return;
                        }
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d("Login", "signInWithEmail:success");
                                            SharedPreferences.Editor setSh = sharedPreferences.edit();
                                            setSh.putInt("IS_LOGIN",1);
                                            Log.i("I",sharedPreferences.getInt("IS_LOGIN",0)+" ");
                                            Intent myIntent = new Intent(v.getContext(), HomeActivity.class);
                                            startActivity( myIntent);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w("Login", "signInWithEmail:failure", task.getException());
                                            Toast.makeText(MainActivity.this, "Sorry Wrong Credeintal !", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}