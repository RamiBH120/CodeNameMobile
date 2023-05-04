package com.org.tn.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class FormHome extends Form {
    public FormHome(Form previous) {
        setTitle("Home");
        setLayout(new FlowLayout(CENTER, CENTER));

//        getToolbar().addCommandToLeftBar("LEFT", null, ev->{});
//        getToolbar().addCommandToRightBar("RIGHT", null, ev->{});

//        getToolbar().addCommandToLeftSideMenu("L1", null, ev->{});
//        getToolbar().addCommandToSideMenu("MENU", null, ev->{});
//        getToolbar().addCommandToRightSideMenu("R1", null, ev->{});


        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_LOGOUT, ev -> { previous.showBack();});

        getToolbar().addCommandToSideMenu("Form1", null, ev->{ new Form1(this).show(); });
        getToolbar().addCommandToSideMenu("Form2", null, ev->{ new Form2(this).show(); });




        Label lbMessage = new Label("Welcome Admin !");

        add(lbMessage);
    }
}
