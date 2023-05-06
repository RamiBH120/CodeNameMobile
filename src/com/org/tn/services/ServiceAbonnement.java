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

    public Abonnement getAbonnementByUser(int iduser) {
        final Abonnement[] abonnement = {new Abonnement()};
        String url = Statics.BASE_URL + "abonnements/existAbonnementByUser?iduser="+iduser;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                abonnement[0] =parseAbonnement(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return abonnement[0];
    }

    public Abonnement getAbonnementByUserOffre(int iduser,int idoffre) {
        final Abonnement[] abonnement = {new Abonnement()};
        String url = Statics.BASE_URL + "abonnements/existAbonnementByUserOffre?iduser="+iduser+"&idoffre="+idoffre;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                if (data != null) {
                    abonnement[0] = parseAbonnement(new String(data));
                    if(abonnement[0]==null) System.out.println("Cannot parse object of abonnement");
                }
                else System.out.println("Response data is null");
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return abonnement[0];
    }

    public boolean addAbonnement(int idoffre,int iduser,String type, double prix) {

        String url = Statics.BASE_URL + "abonnements/add?idoffre="+idoffre+"&iduser="+iduser+"&type="+type+"&prix="+prix;

        req.setUrl(url);
        req.setPost(true);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public Abonnement parseAbonnement(String jsonText) {
        Abonnement abonnement=null;
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> abonnementJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            if( abonnementJson.get("data") instanceof Map){
                Map<String, Object> obj = (Map<String, Object>) abonnementJson.get("data");
                abonnement = new Abonnement();
                abonnement.setId((int) Float.parseFloat(obj.get("id").toString()));
                if (obj.get("user") == null) {
                    abonnement.setUser("null");
                } else {
                    abonnement.setUser(obj.get("user").toString());
                }
                if (obj.get("offre") == null) {
                    abonnement.setOffre("");
                } else {
                    abonnement.setOffre(obj.get("offre").toString());
                }
                abonnement.setType(obj.get("type").toString());
                abonnement.setPrix((int) Float.parseFloat(obj.get("prix").toString()));
                abonnement.setDateDebut(DateHelper.dateFromLongFormat(obj.get("dateDebut").toString()));
                abonnement.setDateFin(DateHelper.dateFromLongFormat(obj.get("dateFin").toString()));

            }
            else System.out.println("Could not read from json");

        } catch (IOException | ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return abonnement;
    }

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

    public void stripePayment(String type,double prix) {
        String url = Statics.BASE_URL + "abonnements/stripe/create-charge?stripeKey=sk_test_51MfrUJCFMOW49kAFTbESKgCT2eFqsze1SJ18WHIRNa7thrhtBprnaL9qZwiDtapzZZxWuuI8DvvcicFolagbl7q7005gJm14xW&type="+type+"&prix="+prix;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                htmlres = new String(req.getResponseData());
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}
