package com.org.tn.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.org.tn.entities.Abonnement;
import com.org.tn.services.ServiceAbonnement;


public class HomeForm extends Form {
    public HomeForm() {

        setTitle("Acceuil");
        setLayout(BoxLayout.y());

        Abonnement abonnement=ServiceAbonnement.getInstance().getAbonnementByUser(1);

        getToolbar().addCommandToSideMenu("Profil", null, ev->{ ; });
        if (abonnement.getOffre().equals("Bronze") || abonnement.getOffre().equals("Silver") || abonnement.getOffre().equals("Gold"))getToolbar().addCommandToSideMenu("Oeuvres", null, ev->{ ; });
        if (abonnement.getOffre().equals("Silver") || abonnement.getOffre().equals("Gold")) getToolbar().addCommandToSideMenu("Evenements", null, ev->{ ; });
        if (abonnement.getOffre().equals("Gold")) getToolbar().addCommandToSideMenu("Ateliers", null, ev->{ ; });
        getToolbar().addCommandToSideMenu("Abonnement", null, ev->{ new AbonnementForm(this).show(); });
        getToolbar().addCommandToSideMenu("Avis", null, ev->{ ; });

    }
}
