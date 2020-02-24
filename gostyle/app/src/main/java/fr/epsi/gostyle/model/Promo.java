package fr.epsi.gostyle.model;

import org.json.JSONObject;

public class Promo {

       private int id;
       private int rate;
       private String item_name;
       private String code;
       private int utilisation_max;
       private String date_debut_validite;
       private String date_fin_validite;

    public Promo(int id, int rate, String item_name, String code, int utilisation_max, String date_debut_validite, String date_fin_validite) {
        this.id = id;
        this.rate = rate;
        this.item_name = item_name;
        this.code = code;
        this.utilisation_max = utilisation_max;
        this.date_debut_validite = date_debut_validite;
        this.date_fin_validite = date_fin_validite;
    }

    public Promo(JSONObject jsonObject){
        this.id = jsonObject.optInt("id");
        this.rate = jsonObject.optInt("rate");
        this.item_name = jsonObject.optString("item_name");
        this.code = jsonObject.optString("code");
        this.utilisation_max = jsonObject.optInt("utilisation_max");
        this.date_debut_validite = jsonObject.optString("date_debut_validite");
        this.date_fin_validite = jsonObject.optString("date_fin_validite");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUtilisation_max() {
        return utilisation_max;
    }

    public void setUtilisation_max(int utilisation_max) {
        this.utilisation_max = utilisation_max;
    }

    public String getDate_debut_validite() {
        return date_debut_validite;
    }

    public void setDate_debut_validite(String date_debut_validite) {
        this.date_debut_validite = date_debut_validite;
    }

    public String getDate_fin_validite() {
        return date_fin_validite;
    }

    public void setDate_fin_validite(String date_fin_validite) {
        this.date_fin_validite = date_fin_validite;
    }
}
