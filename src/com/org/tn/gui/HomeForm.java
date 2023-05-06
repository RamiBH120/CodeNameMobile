package com.org.tn.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;


public class HomeForm extends Form {
    public HomeForm() {

        setTitle("Acceuil");
        setLayout(BoxLayout.y());




        getToolbar().addCommandToSideMenu("Profil", null, ev->{ ; });
        getToolbar().addCommandToSideMenu("Oeuvres", null, ev->{ ; });
        getToolbar().addCommandToSideMenu("Evenements", null, ev->{ ; });
        getToolbar().addCommandToSideMenu("Ateliers", null, ev->{ ; });
        getToolbar().addCommandToSideMenu("Abonnement", null, ev->{ new AbonnementForm(this).show(); });
        getToolbar().addCommandToSideMenu("Avis", null, ev->{ ; });

    }
}
