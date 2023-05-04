package com.org.tn.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.org.tn.entities.Offre;
import com.org.tn.services.ServiceAbonnement;
import com.org.tn.services.ServiceOffre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaymentForm extends Form {
    public PaymentForm(Form previous, Offre offre) {
        setTitle("Paiement");
        setLayout(BoxLayout.y());

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> {
            previous.showBack();
        });

        Label lbType = new Label("Type d'offre: "+offre.getType());
        Label lbPrix = new Label("Prix: "+ offre.getPrix());
        Label lbPromotion = new Label("Promotion: "+ offre.getPromotion());
        Label lbDateDebut = new Label("Date Début: "+ offre.getDateDebut());
        Label lbDateFin = new Label("Date Fin: "+ offre.getDateFin());
        Label lbDescription = new Label("Description: "+ offre.getDateFin());

        Label choix = new Label("Veuillez choisir un type d'abonnement pour continuer");

        Button btnsub = new Button("S'abonner");
        btnsub.addActionListener(e->{
            if (Dialog.show("Confirmation", "Voullez vous vous abonner à cette offre "+offre.getType()+'?', "Oui", "Non")) {
                /*SerieService ss = new SerieService();
                ss.ajouterSerie(s);*/
                Dialog.show("Confirmation", "Félicitations!, vous êtes abonné à l'offre "+offre.getType(), "Oui", null);
            }
        });
        //combobox start
        ComboBox<Map<String, Object>> combo = new ComboBox<> (
                createListEntry("Hebdomadaire", String.valueOf((offre.getPrix()+5.5)-((offre.getPrix()+5.5)*(offre.getPromotion()/100)))+'€'),
                createListEntry("Mensuel", String.valueOf((offre.getPrix()+12.5)-((offre.getPrix()+12.5)*(offre.getPromotion()/100)))+'€'),
                createListEntry("Annuel", String.valueOf((offre.getPrix()+45.5)-((offre.getPrix()+45.5)*(offre.getPromotion()/100)))+'€'));

        combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
        //combobox end
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

            addAll(lbType,imgv,lbDescription, lbPrix,lbPromotion,lbDateDebut,lbDateFin,choix,combo,btnsub);
        }
        else addAll(lbType,lbDescription, lbPrix,lbPromotion,lbDateDebut,lbDateFin,choix,combo,btnsub);

        /*String payment = ServiceAbonnement.getInstance().stripePayment();

        BrowserComponent browser = new BrowserComponent();
        //browser.setURL("https://www.codenameone.com/");

        String s = "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>My First Heading</h1>\n" +
                "\n" +
                "<p>My first paragraph.</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";

        browser.setPage(payment,null);
        add(browser);*/


    }

    private Map<String, Object> createListEntry(String name, String date) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", date);
        return entry;
    }
}
