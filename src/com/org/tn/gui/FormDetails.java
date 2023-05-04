package com.org.tn.gui;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class FormDetails extends Form {
    public FormDetails(Form previous, String nom, String prenom, String pwd, String genre, String hobbies) {
        setTitle("Details");
        setLayout(BoxLayout.y());

        getToolbar().addCommandToLeftBar("return", null, ev->{
            previous.showBack();
        });

        Label lbNom = new Label(nom);
        Label lbPrenom = new Label(prenom);
        Label lbPassword = new Label(pwd);
        Label lbGenre = new Label(genre);
        Label lbHobbies = new Label(hobbies);

        addAll(lbNom, lbPrenom, lbPassword, lbGenre, lbHobbies);


    }
}
