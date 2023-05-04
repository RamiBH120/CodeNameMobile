package com.org.tn.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class Form1 extends Form {
    public Form1(Form previous) {
        setTitle("FORM 1");
        setLayout(new FlowLayout(CENTER, CENTER));
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> {
            previous.showBack();
        });

        getToolbar().addCommandToSideMenu("Form1", null, ev->{ new Form1(this).show(); });
        getToolbar().addCommandToSideMenu("Form2", null, ev->{ new Form2(this).show(); });

        add(new Label("FORM 1"));
    }
}
