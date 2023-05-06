package com.org.tn.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.org.tn.entities.Offre;
import com.org.tn.entities.Offre;
import com.org.tn.entities.Offre;
import com.org.tn.utils.DateHelper;
import com.org.tn.utils.Statics;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServiceOffre {
    public ArrayList<Offre> Offres;

    public static ServiceOffre instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceOffre() {
        req = new ConnectionRequest();
    }

    public static ServiceOffre getInstance() {
        if (instance == null) {
            instance = new ServiceOffre();
        }
        return instance;
    }

    public ArrayList<Offre> parseOffres(String jsonText) {
        try {
            Offres = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> OffresListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) OffresListJson.get("data");
            for (Map<String, Object> obj : list) {
                Offre offre = new Offre();
                offre.setId((int) Float.parseFloat(obj.get("id").toString()));
                if (obj.get("image") == null) {
                    offre.setImage("null");
                } else {
                    offre.setImage(obj.get("image").toString());
                }
                if (obj.get("description") == null) {
                    offre.setDescription("");
                } else {
                    offre.setDescription(obj.get("description").toString());
                }
                offre.setType(obj.get("type").toString());
                offre.setPrix((int) Float.parseFloat(obj.get("prix").toString()));
                offre.setPromotion((int) Float.parseFloat(obj.get("promotion").toString()));
                offre.setDateDebut(DateHelper.dateFromLongFormat(obj.get("dateDebut").toString()));
                offre.setDateFin(DateHelper.dateFromLongFormat(obj.get("dateFin").toString()));

                Offres.add(offre);
            }

        } catch (IOException | ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return Offres;
    }

    public Offre parseOffre(String jsonText) {
        Offre offre=null;
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> offreJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            if( offreJson.get("data") instanceof Map){
                Map<String, Object> obj = (Map<String, Object>) offreJson.get("data");
                offre = new Offre();
                offre.setId((int) Float.parseFloat(obj.get("id").toString()));
                if (obj.get("image") == null) {
                    offre.setImage("null");
                } else {
                    offre.setImage(obj.get("image").toString());
                }
                if (obj.get("description") == null) {
                    offre.setDescription("");
                } else {
                    offre.setDescription(obj.get("description").toString());
                }
                offre.setType(obj.get("type").toString());
                offre.setPrix((int) Float.parseFloat(obj.get("prix").toString()));
                offre.setPromotion((int) Float.parseFloat(obj.get("promotion").toString()));
                offre.setDateDebut(DateHelper.dateFromLongFormat(obj.get("dateDebut").toString()));
                offre.setDateFin(DateHelper.dateFromLongFormat(obj.get("dateFin").toString()));

            }
            else System.out.println("Could not read from json");

        } catch (IOException | ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return offre;
    }

    public Offre getOffre(int idoffre) {
        final Offre[] offre = {new Offre()};
        String url = Statics.BASE_URL + "offres/get?idoffre="+idoffre;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                offre[0] =parseOffre(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offre[0];
    }
    
    public ArrayList<Offre> getAllOffres() {
        String url = Statics.BASE_URL + "offres/list/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Offres = parseOffres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Offres;
    }
}
