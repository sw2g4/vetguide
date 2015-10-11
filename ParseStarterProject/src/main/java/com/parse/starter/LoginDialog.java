package com.parse.starter;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



public class LoginDialog extends DialogFragment implements View.OnClickListener{
    Button login, cancel;
    EditText usernameET, passwordET;
    Communicator communicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog,null);
        usernameET = (EditText)view.findViewById(R.id.login_user);
        passwordET = (EditText)view.findViewById(R.id.login_pass);
        cancel = (Button)view.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(this);
        login = (Button)view.findViewById(R.id.btn_login);
        login.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_login){
            communicator.onDialogMessage(usernameET.getText().toString().trim(), passwordET.getText().toString().trim());
            dismiss();
        }else{

        }

    }
    interface Communicator{
        public void onDialogMessage(String user, String pass);
    }
}
