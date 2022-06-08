package com.example.restaurant.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.restaurant.MenuPrincipal;
import com.example.restaurant.NavigationDrawer;
import com.example.restaurant.R;
import com.example.restaurant.model.Restaurante;
import com.example.restaurant.model.User;
import com.example.restaurant.viewmodel.RestauranteViewModel;
import com.example.restaurant.viewmodel.UserViewModel;
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

import java.util.HashMap;

public class LoginRestaurante extends AppCompatActivity {


    private RestauranteViewModel restauranteViewModel;
    private EditText email, password;
    private Button btnLogin;
    private SignInButton btnGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private final  static int RC_SIGN_IN=123;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_restaurante);

        btnLogin =findViewById(R.id.btnLoginRestaurante);
        email = findViewById(R.id.txtEmailRestaurante);
        password = findViewById(R.id.textpasswordRestaurante);

        crearSolicitud();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configurarModel();
                init();
            }
        });


    }

    //pasar los parametros al metodo del viewModel
    public void  init(){
        String ema = email.getText().toString();
        String pass = password.getText().toString();

        if (email.length() ==0 || password.length() == 0 ) {

            Toast.makeText(LoginRestaurante.this, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
        }

        restauranteViewModel.setRestauranteRepository(ema,pass);
    }

    //pasar al activity de registro de restaurante
    public  void registrarse(View view){
        Intent intent = new Intent(LoginRestaurante.this,RegistroRestaurante.class);
        startActivity(intent);
    }

    //Hacer el llamado al service de busqueda de usuario
    public void configurarModel(){
        restauranteViewModel = new ViewModelProvider(this).get(RestauranteViewModel.class);
        restauranteViewModel.init();
        restauranteViewModel.getObserverRestaurante().observe(this, new Observer<Restaurante>() {
            @Override
            public void onChanged(Restaurante restaurante) {
                if (restaurante != null){
                    Toast.makeText(LoginRestaurante.this,"Registrado correctamente",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginRestaurante.this, Opcion.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginRestaurante.this,"Contraseña o email incorrectos",Toast.LENGTH_LONG).show();

                }
            }
        });

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

                            if (task.getResult().getAdditionalUserInfo().isNewUser()){
                                String uid = user.getUid();
                                String correo = user.getEmail();
                                String nombre = user.getDisplayName();

                                HashMap<Object,String> datosUsuario = new HashMap<>();

                                datosUsuario.put("uid",uid);
                                datosUsuario.put("correo",correo);
                                datosUsuario.put("nombre",nombre);

                                startActivity(new Intent(LoginRestaurante.this, MenuPrincipal.class));
                            }
                            else{
                                Toast.makeText(LoginRestaurante.this,"No tiene cuenta",Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                });
    }
}