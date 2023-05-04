package com.org.tn.gui;

import com.codename1.components.Switch;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

public class FormInscription extends Form {
    public FormInscription() {
        setTitle("Inscription");
        setLayout(BoxLayout.y());

        TextField tfNom = new TextField("", "Nom..");
        TextField tfPrenom = new TextField("", "Prenom..");
        TextField tfPassword = new TextField("", "Password..", 0, TextField.PASSWORD);
//        tfPassword.setConstraint(TextField.PASSWORD);

        Label lbHomme = new Label("Homme");
        Switch gender = new Switch();
        Label lbFemme = new Label("Femme");
        Container cn1 = new Container(BoxLayout.x());
        cn1.addAll(lbHomme, gender, lbFemme);

        CheckBox cbFoot = new CheckBox("Foot");
        CheckBox cbLecture = new CheckBox("Lecture");
        CheckBox cbTennis = new CheckBox("Tennis");
        CheckBox cbNatation = new CheckBox("Natation");

        Button btnValider = new Button("Valider");

        btnValider.addActionListener(e -> {

            String nom ="", prenom="", password="", genre="Homme", hobbies="";

            nom = tfNom.getText();
            prenom = tfPrenom.getText();
            password = tfPassword.getText();

            if (gender.isOn()) {
                genre="Femme";
            }

            if (cbFoot.isSelected()) {
                hobbies += "Foot ";
            }
            if (cbLecture.isSelected()) {
                hobbies += "Lecture ";
            }
            if (cbNatation.isSelected()) {
                hobbies += "Natation ";
            }
            if (cbTennis.isSelected()) {
                hobbies += "Tennis ";
            }

            new FormDetails(this ,nom, prenom, password, genre, hobbies).show();

        });

        addAll(tfNom, tfPrenom, tfPassword, cn1, cbFoot, cbLecture,cbNatation, cbTennis, btnValider);
    }
}
