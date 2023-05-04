package com.org.tn.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
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

    /*public boolean addOffre(Offre t) {

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

    public ArrayList<Offre> getAllOffres() {
        String url = Statics.BASE_URL + "offres/get/";
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
