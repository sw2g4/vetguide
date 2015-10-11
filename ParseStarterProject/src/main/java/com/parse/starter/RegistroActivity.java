package com.parse.starter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class RegistroActivity extends Activity {
    private EditText usuarioET;
    private EditText contrasenaET;
    private EditText nombreET;
    private EditText apellidoET;
    private EditText dniET;


    private EditText nombreMascotaET;
    private EditText TipoMascotaET;
    private EditText RazaMascotaET;
    private EditText ColorMascotaET;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuarioET = (EditText)findViewById(R.id.user);
        contrasenaET = (EditText)findViewById(R.id.password);
        nombreET = (EditText)findViewById(R.id.nombre);
        apellidoET = (EditText)findViewById(R.id.apellido);
        //dniET = (EditText)findViewById(R.id.dni);


        nombreMascotaET = (EditText)findViewById(R.id.nombreMascota);
        TipoMascotaET = (EditText)findViewById(R.id.TipoMascota);

        RazaMascotaET = (EditText)findViewById(R.id.RazaMascota);
        //ColorMascotaET = (EditText)findViewById(R.id.ColorMascota);





        Button btn_guardar = (Button)findViewById(R.id.butGuardar);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
       });
    }

    private void signup() {
        String username = usuarioET.getText().toString().trim();
        String password = contrasenaET.getText().toString().trim();
        String name = nombreET.getText().toString().trim();
        String apellido = apellidoET.getText().toString().trim();
        //String dni = dniET.getText().toString().trim();

        String NombreMascota = nombreMascotaET.getText().toString().trim();
        String TipoMascota = TipoMascotaET.getText().toString().trim();
        String RazaMascota = RazaMascotaET.getText().toString().trim();
       // String ColorMascota = ColorMascotaET.getText().toString().trim();


        // Validate the sign up data
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if (username.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_username));
        }
        if (password.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_password));
        }
        validationErrorMessage.append(getString(R.string.error_end));

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(RegistroActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // Set up a progress dialog
        final ProgressDialog dialog = new ProgressDialog(RegistroActivity.this);
        dialog.setMessage(getString(R.string.progress_signup));
        dialog.show();

        // Set up a new Parse user
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.put("nombre", name);
        user.put("Apellido", apellido);
        //user.put("dni", dni);
        user.put("NombreMascota",NombreMascota);
        user.put("TipoMascota",TipoMascota);
        user.put("RazaMascota",RazaMascota);
        //user.put("ColorMascota",ColorMascota);

        // Call the Parse signup method
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                dialog.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(RegistroActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Start an intent for the dispatch activity
                    Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}
