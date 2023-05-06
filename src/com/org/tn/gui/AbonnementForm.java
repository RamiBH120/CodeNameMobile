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
        Button btnAddTask = new Button("Mon abonnement");
        Button btnListTasks = new Button("List des offres");

        //getToolbar().addCommandToSideMenu("Mon abonnement", null, ev->{ new Form1(this).show(); });
        getToolbar().addCommandToSideMenu("List des offres", null, ev->{ new ListOffres(this).show(); });

        btnListTasks.addActionListener(e-> new ListOffres(this).show());
        addAll(btnAddTask,btnListTasks);
    }
}
