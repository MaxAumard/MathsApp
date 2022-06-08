package com.example.projets6.activity;



import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;
import java.util.regex.Pattern;

import com.example.projets6.R;
import com.example.projets6.back.DbConnector;

public class logInActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText userField;
    private EditText passwordField;//en XML  android:inputType="Password"
    private EditText confirmPasswordField;
   // public Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    //Pattern.compile("^(.+)@(.+)$");
    public Pattern p=Pattern.compile("^[A-Z]+[A-Z0-9._-]+");
    private TextView userMessage;
    private TextView signUpMessage;
    private EditText signUpName;
    private EditText signUpUser;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        loginBtn=(Button)findViewById(R.id.logInBtn);
        userField=(EditText) findViewById(R.id.userField);
        passwordField=(EditText) findViewById(R.id.passwordField);
       // confirmPasswordField=findViewById(R.id.confirmPasswordField);
        userMessage=(TextView) findViewById(R.id.userMessage);
        //signInMessage=findViewById(R.id.signInMessage);
        //signInUser=findViewById(R.id.)
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectBtn(v);
                Log.i("DEBUG","clicked");
            }
        });



    }
    //private  String passEncrypted=getSHA(passwordField.getText().toString());
    //private String confirmPassEncrypted=getSHA(confirmPasswordField.getText().toString());
    private String passEncrypted=getSHA("TEST");
    private String confirmPassEncrypted=getSHA("ee");
    public boolean loginFormIsValid() {
        if (!p.matcher(userField.getText()).matches()) {
            userMessage.setText("Wrong !");
            return false;
        } else {
            userMessage.setText("true");
            return true;
        }
    }

/*    public boolean signupFormIsValid() {
        if (!p.matcher(userField.getText()).matches()) {
            signUpMessage.setText("Wrong email !");
            return false;
        } else if (!passEncrypted.equals(confirmPassEncrypted)) {
            signUpMessage.setText("Passwords don't match");
            return false;
        } else {
            signUpMessage.setText("");
            return true;
        }
    }*/

    public void connectBtn(View view) {
        DbConnector connect = new DbConnector();
        Connection connectDB = connect.getConnection();
        Log.i("DEBUG","on fucntion");
        if (loginFormIsValid()) {
            try {
                String connectQuery = "SELECT id,name,email, password FROM player WHERE email='" + userField.getText() + "' and password='" + passEncrypted + "';";
                Statement statement = connectDB.createStatement();
                ResultSet queryOutput = statement.executeQuery(connectQuery);

                if (queryOutput.next()) {
                    if (queryOutput.getString("email").contains(userField.getText()) && queryOutput.getString("password").contains(passEncrypted)) {
                        //then show l'interface de jeu
                    }

                } else {
                    userMessage.setText("Account not found !");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*else if(signupFormIsValid()) {
            try{
                String connectQuery = "select count(id) from player;";
                Statement statement = connectDB.createStatement();
                ResultSet queryOutput = statement.executeQuery(connectQuery);
                queryOutput.next();
                //int current_id = queryOutput.getInt(1) + 1;
                String name =signUpName.getText().toString();
                String createQuery = String.format("INSERT INTO player(userName,password) VALUES('%s','%s')",userField.getText(), passEncrypted);
                statement.executeUpdate(createQuery);
                signUpMessage.setText("Account created successfully !");
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }


    public static String getSHA(String input) {

        try {

            // Static getInstance method is called with hashing SHA
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // digest() method called
            // to calculate message digest of an input
            // and return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown"
                    + " for incorrect algorithm: " + e);

            return null;
        }
    }


}

