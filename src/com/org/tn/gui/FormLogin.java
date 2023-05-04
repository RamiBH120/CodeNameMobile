package com.org.tn.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

public class FormLogin extends Form {
    public FormLogin() {
        setTitle("Form Login");
        setLayout(new FlowLayout(CENTER, CENTER));

        Container cn1 = new Container(BoxLayout.y());
        TextField tfLogin = new TextField("", "Login");
        TextField tfPwd = new TextField("", "Password", 0, TextField.PASSWORD);
        Button btnValider = new Button("Valider");

        btnValider.addActionListener(e->{

            if (tfLogin.getText().equals("admin") && tfPwd.getText().equals("admin")) {
                new FormHome(this).show();
            }else{
                Dialog.show("Erreur", "Utilisateur non connue !", "OK", null);
            }

        });

        cn1.addAll(tfLogin, tfPwd, btnValider);

        add(cn1);
    }
}
