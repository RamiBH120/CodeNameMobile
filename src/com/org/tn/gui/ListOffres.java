package com.org.tn.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.org.tn.entities.Offre;
import com.org.tn.services.ServiceOffre;

import java.io.IOException;
import java.util.ArrayList;


public class ListOffres extends Form {
    public ListOffres(Form previous) {
        setTitle("List offres");
        setLayout(BoxLayout.y());

        ArrayList<Offre> offres = ServiceOffre.getInstance().getAllOffres();
        for (Offre o : offres) {
            add(addElement(o));
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public Container addElement(Offre offre) {

        Container cn1 = new Container(BoxLayout.x());
        Container cn2 = new Container(BoxLayout.y());

        Container cn3 = new Container(BoxLayout.y());
        Label lbTitle = new Label(offre.getType());
        Label lbPrix = new Label(offre.getPrix() +"€");
        Label lbPromotion = new Label(offre.getPromotion() + "%");

        Button btnsub = new Button("S'abonner");
        Button btnplus = new Button("Description");

        btnplus.addActionListener(e->{
            Dialog.show("Détails Offre "+offre.getType(), offre.getDescription()+"\nDate début: "+offre.getDateDebut()+"\nDate fin: "+offre.getDateFin(), "Fermer", null);
        });
        btnsub.addActionListener(e->{
            new PaymentForm(this,offre).show();
        });

        cn2.addAll(lbTitle, lbPrix, lbPromotion);
        cn3.addAll(btnplus,btnsub);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth() / 2, this.getWidth() / 5, 0xffff0000), true);

        ImageViewer imgv;
        String url = offre.getImage();

        if(url != null) {
            ConnectionRequest.setDefaultUserAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:68.0) Gecko/20100101 Firefox/68.0");
            URLImage background = URLImage.createToStorage(placeholder, url, url, URLImage.RESIZE_SCALE_TO_FILL);

            //imgs = URLImage.createToStorage(placeholder, url, url, URLImage.RESIZE_SCALE);
            //premier url est le nom de l'image et le deuxieme est l'image elle même
            imgv = new ImageViewer(background);
            cn1.addAll(imgv, cn2,cn3);
        }
        else cn1.addAll(cn2,cn3);

        /*lbTitle.addPointerReleasedListener(evt -> {
            if (Dialog.show("Confirmation", "Voullez vous vous abonner à cette offre ?", "Oui", "Non")) {
                Dialog.show("Confirmation", "La serie est ajoute avec success !", "Oui", null);
            }
        });*/

        //cn1.setLeadComponent(lbTitle);

        return cn1;
    }
}
