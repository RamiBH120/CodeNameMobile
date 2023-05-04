package com.org.tn.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.org.tn.entities.Abonnement;
import com.org.tn.utils.DateHelper;
import com.org.tn.utils.Statics;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceAbonnement {
    public ArrayList<Abonnement> Abonnements;

    public static ServiceAbonnement instance = null;
    public boolean resultOK;

    public String htmlres;
    private ConnectionRequest req;

    private ServiceAbonnement() {
        req = new ConnectionRequest();
    }

    public static ServiceAbonnement getInstance() {
        if (instance == null) {
            instance = new ServiceAbonnement();
        }
        return instance;
    }

    /*public boolean addAbonnement(Abonnement t) {

        String name = t.getName();
        int status =  t.getStatus();

        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "create/" + status + "/" + name;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }*/

    public ArrayList<Abonnement> parseAbonnements(String jsonText) {
        try {
            Abonnements = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> AbonnementsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) AbonnementsListJson.get("data");
            for (Map<String, Object> obj : list) {
                Abonnement Abonnement = new Abonnement();
                Abonnement.setId((int) Float.parseFloat(obj.get("id").toString()));
                if (obj.get("user") == null) {
                    Abonnement.setUser("null");
                } else {
                    Abonnement.setUser(obj.get("user").toString());
                }
                if (obj.get("offre") == null) {
                    Abonnement.setOffre("");
                } else {
                    Abonnement.setOffre(obj.get("offre").toString());
                }
                Abonnement.setType(obj.get("type").toString());
                Abonnement.setPrix((int) Float.parseFloat(obj.get("prix").toString()));
                Abonnement.setDateDebut(DateHelper.dateFromLongFormat(obj.get("dateDebut").toString()));
                Abonnement.setDateFin(DateHelper.dateFromLongFormat(obj.get("dateFin").toString()));

                Abonnements.add(Abonnement);
            }

        } catch (IOException | ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return Abonnements;
    }

    public ArrayList<Abonnement> getAllAbonnements() {
        String url = Statics.BASE_URL + "abonnements/get/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Abonnements = parseAbonnements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Abonnements;
    }

    public String stripePayment() {
        String url = Statics.BASE_URL + "abonnements/checkout/";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("id","1");
        req.addArgument("type","Mensuel");
        req.addArgument("prix","15");
        req.addArgument("apiKey","sk_test_51MfrUJCFMOW49kAFTbESKgCT2eFqsze1SJ18WHIRNa7thrhtBprnaL9qZwiDtapzZZxWuuI8DvvcicFolagbl7q7005gJm14xW");
        /*
        req.addRequestHeader("id","1");
        req.addRequestHeader("type","Mensuel");
        req.addRequestHeader("prix","15");*/

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                htmlres = new String(req.getResponseData());
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return htmlres;
    }
}
