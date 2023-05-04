package com.org.tn.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class Form2 extends Form {
    public Form2(Form previous) {
        setTitle("FORM 2");
        setLayout(new FlowLayout(CENTER, CENTER));
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> {
            previous.showBack();
        });

        add(new Label("FORM 2"));
    }
}
