package fr.epsi.gostyle.model;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Class Promo
 */
public class Promo implements Serializable {

    private int id;
    private int rate;
    private String item_name;
    private String code;
    private int utilisation_max;
    private String date_fin_validite;
    private int nb_utilisation;

    /**
     * Constructeur
     * @param id
     * @param rate
     * @param item_name
     * @param code
     * @param utilisation_max
     * @param date_fin_validite
     * @param nb_utilisation
     */
    public Promo(int id, int rate, String item_name, String code, int utilisation_max, String date_fin_validite, int nb_utilisation) {
        this.id = id;
        this.rate = rate;
        this.item_name = item_name;
        this.code = code;
        this.utilisation_max = utilisation_max;
        this.date_fin_validite = date_fin_validite;
        this.nb_utilisation = nb_utilisation;
    }

    /**
     * Constructeur qui prend un obejct JSON en paramètre
     * @param jsonObject
     */
    public Promo(JSONObject jsonObject){
        this.id = jsonObject.optInt("id");
        this.rate = jsonObject.optInt("rate");
        this.item_name = jsonObject.optString("item_name");
        this.code = jsonObject.optString("code");
        this.utilisation_max = jsonObject.optInt("utilisation_max");
        this.date_fin_validite = jsonObject.optString("date_fin_validite");
        this.nb_utilisation = jsonObject.optInt("nb_utilisation");
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

    public String getDate_fin_validite() {
        return date_fin_validite;
    }

    public void setDate_fin_validite(String date_fin_validite) {
        this.date_fin_validite = date_fin_validite;
    }

    public int getNb_utilisation() {
        return nb_utilisation;
    }

}
