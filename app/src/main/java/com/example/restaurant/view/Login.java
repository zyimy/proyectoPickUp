package com.example.restaurant.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.restaurant.NavigationDrawer;
import com.example.restaurant.R;
import com.example.restaurant.model.User;
import com.example.restaurant.viewmodel.UserViewModel;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Login extends AppCompatActivity {

    private UserViewModel userViewModel;
    private EditText email, password;
    private Button btnLogin;
    private SignInButton btnGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private final  static int RC_SIGN_IN=123;
    private FirebaseAuth firebaseAuth;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private CheckBox sesion;
    private String llave = "sesion",contrasenas,emails;

    @Override
    protected void onStart(){
        super.onStart();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user!=null){
            startActivity(new Intent(Login.this,NavigationDrawer.class));

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin =findViewById(R.id.btnLoginUsuario);
        btnGoogle = findViewById(R.id.btnGoogle);
        email = findViewById(R.id.txtEmailUsuario);
        password = findViewById(R.id.textpasswordUsuario);
        sesion = findViewById(R.id.ckSesion);
        sharedPreferences = this.getSharedPreferences("sesiones",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        firebaseAuth = FirebaseAuth.getInstance();

        crearSolicitud();

        if (revisarSession()){
            Intent intent = new Intent(Login.this,NavigationDrawer.class);
            startActivity(intent);
        }else {
            Toast.makeText(Login.this,"Inicie sesion",Toast.LENGTH_LONG).show();

        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                configurarModel();
                init();

            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singIn();

            }
        });

        //googleLogin();

    }

    //Guarda la session de usuario
    public void guardarSession(boolean checked,String contrasena,String email){
       editor.putBoolean("sesion",checked);
       editor.putString("contrasena",contrasena);
       editor.putString("nombre",email);
       editor.apply();
    }


    //Revisa la session de usuario
    public boolean revisarSession(){
        boolean sesion = this.sharedPreferences.getBoolean(llave,false);
        return sesion;
    }

    //Crear la solicitud
    private void crearSolicitud(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Creamos el googleSingnClient
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
    }


    //Crear la pantalla de google

    private void singIn(){
       Intent signIntent = mGoogleSignInClient.getSignInIntent();
       startActivityForResult(signIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       //resultado devuelto al iniciar la intencion desde Google SignInApi.getSignIntent
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //el inicio de session fue exitoso, autentique con firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                autenticacionFirebase(account);//aqui se ejecuta el metodo para logearnos con google
            }catch (ApiException e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }

    //Metodo pra autenticar con firebase - google
    private void autenticacionFirebase(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //se inicio correctamente
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                                 startActivity(new Intent(Login.this,NavigationDrawer.class));
                            }
                            else{
                                Toast.makeText(Login.this,"No tiene cuenta",Toast.LENGTH_LONG).show();

                            }
                        }

                });
    }


    //pasamos los valores de email y contraseña y configuramos si estan llenos o no
    //tambien pasamos esos valores al observador
    public void  init(){
        String ema = email.getText().toString();
        String pass = password.getText().toString();

        if (email.length() ==0 || password.length() == 0 ) {

            Toast.makeText(Login.this, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
        }

        userViewModel.setUserRepository(ema,pass);
    }

    //Si el  usuario no esta registrado los manda ala pantalla de registro
    public  void registrarse(View view){
        Intent intent = new Intent(Login.this,RegistroUser.class);
        startActivity(intent);
    }

    //configuramos el observador para que observe cualuier cambio y se lo pase al activity
    public void configurarModel(){
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init();
        userViewModel.getObserverUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null){
                  contrasenas = user.getContrasena();
                  emails = user.getEmail();
                    guardarSession(sesion.isChecked(),contrasenas,emails);
                    Toast.makeText(Login.this,"Registrado correctamente",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, NavigationDrawer.class);
                    startActivity(intent);


                }else{
                    Toast.makeText(Login.this,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
/*
    public void googleLogin(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build();
                signInLauncher.launch(signInIntent);
            }
        });

    }
/*
    // See: https://developer.android.com/training/basics/intents/result
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

 */
/*
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Toast.makeText(this,"Bienvenido "+user.getDisplayName(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Login.this, NavigationDrawer.class);
            startActivity(intent);

            // ...
        } else {
            Toast.makeText(this,"Usuario incorrecto ",Toast.LENGTH_LONG).show();

            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

 */



}