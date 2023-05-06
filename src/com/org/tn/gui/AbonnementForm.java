package com.org.tn.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class AbonnementForm extends Form {
    public AbonnementForm(Form previous){
        setTitle("Abonnements");
        setLayout(BoxLayout.y());

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> {
            previous.showBack();
        });

        add(new Label("Choisir une option"));
        Button btnMonAbonnement = new Button("Mon abonnement");
        Button btnListOffres = new Button("List des offres");


        btnMonAbonnement.addActionListener(e-> new MonAbonnement(this).show());
        btnListOffres.addActionListener(e-> new ListOffres(this).show());
        addAll(btnMonAbonnement,btnListOffres);
    }
}
