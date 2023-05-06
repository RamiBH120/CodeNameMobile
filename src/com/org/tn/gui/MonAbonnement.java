package com.org.tn.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.org.tn.entities.Abonnement;
import com.org.tn.entities.Offre;
import com.org.tn.services.ServiceAbonnement;
import com.org.tn.services.ServiceOffre;

public class MonAbonnement extends Form {
    public MonAbonnement(Form previous){
        setTitle("List offres");
        setLayout(BoxLayout.y());

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        Abonnement abonnement= ServiceAbonnement.getInstance().getAbonnementByUser(1);

        Offre offre = ServiceOffre.getInstance().getOffre(abonnement.getIdoffre());

        Label lbType = new Label("Type d'abonnement: "+abonnement.getType());
        Label lbPrix = new Label("Prix abonnement: "+ abonnement.getPrix() +"€");
        Label lbDateDebut = new Label("Date d'achat: "+ abonnement.getDateDebut());
        Label lbDateFin = new Label("Date d'expiration: "+ abonnement.getDateFin());
        Label lbOffreType = new Label("Sous l'offre: "+ offre.getType());
        Label lbPromotion = new Label("Promotion de l'offre: "+ offre.getPromotion()+"%");
        Label lbDescription = new Label("Description de l'offre: "+ offre.getDescription());

        //image start
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth(), this.getWidth() / 5, 0xffff0000), true);

        ImageViewer imgv;
        String url = offre.getImage();

        if(url != null) {
            ConnectionRequest.setDefaultUserAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:68.0) Gecko/20100101 Firefox/68.0");
            URLImage background = URLImage.createToStorage(placeholder, url, url, URLImage.RESIZE_SCALE_TO_FILL);

            //imgs = URLImage.createToStorage(placeholder, url, url, URLImage.RESIZE_SCALE);
            //premier url est le nom de l'image et le deuxieme est l'image elle même
            imgv = new ImageViewer(background);
            //image end

            addAll(lbType,imgv, lbPrix,lbDateDebut,lbDateFin,lbOffreType,lbPromotion,lbDescription);
        }
        else addAll(lbType,lbPrix,lbDateDebut,lbDateFin,lbOffreType,lbPromotion,lbDescription);

    }
}
