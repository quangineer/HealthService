package com.example.healthcareservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextAppFullName);
        edEmail = findViewById(R.id.editTextAppAddress);
        edPassword = findViewById(R.id.editTextAppContactNumber);
        edConfirm = findViewById(R.id.editTextAppFees);
        btn = findViewById(R.id.buttonBookAppointment);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if(email.length() != 0){
                    if(emailValid(email)){}
                    else{
                        Toast.makeText(getApplicationContext(),"Invalid email",Toast.LENGTH_SHORT).show();
                    };
                }

                if(username.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill All details",Toast.LENGTH_SHORT).show();
                } else {
                    if(password.compareTo(confirm)==0){
                        if(isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(),"Record Inserted",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        } else{
                            Toast.makeText(getApplicationContext(),"Password must contain at least 8 combined characters,letters,digits and special symbols",Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(getApplicationContext(), "Password and Confirmed Password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean emailValid(String emailReview){
        boolean hasAtSymbol = emailReview.contains("@");
        boolean hasMailWord = emailReview.toLowerCase().contains("mail");
        return hasAtSymbol && hasMailWord;
    }

    public static boolean isValid(String passwordReview){
        int f1=0,f2=0,f3=0;
        if(passwordReview.length() < 8){
            return false;
        } else {
            for(int p = 0; p < passwordReview.length(); p++){
                if(Character.isLetter(passwordReview.charAt(p))){
                    f1=1;
                }
            }
            for(int q = 0; q < passwordReview.length(); q++){
                if(Character.isDigit(passwordReview.charAt(q))){
                    f2=1;
                }
            }
            for(int r = 0; r < passwordReview.length(); r++){
                char c = passwordReview.charAt(r);
                if(c>=33&&c<=46||c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
}