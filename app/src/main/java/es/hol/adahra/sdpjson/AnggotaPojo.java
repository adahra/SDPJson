package es.hol.adahra.sdpjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blackshadow on 19/07/16.
 */
public class AnggotaPojo {
    // @SerializedName("id")
    // @Expose
    private String id;
    // @SerializedName("nama")
    // @Expose
    private String nama;
    // @SerializedName("username")
    // @Expose
    private String username;
    // @SerializedName("password")
    // @Expose
    private String password;
    // @SerializedName("alamat")
    // @Expose
    private String alamat;
    // @SerializedName("latitude")
    // @Expose
    private String latitude;
    // @SerializedName("longitude")
    // @Expose
    private String longitude;
    // @SerializedName("foto")
    // @Expose
    private String foto;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The nama
     */
    public String getNama() {
        return nama;
    }

    /**
     *
     * @param nama
     * The nama
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     * The alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     *
     * @param alamat
     * The alamat
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     *
     * @return
     * The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     *
     * @param foto
     * The foto
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    public class DataAnggota {
        @SerializedName("data")
        @Expose
        private List<AnggotaPojo> data = new ArrayList<AnggotaPojo>();

        /**
         * @return The data
         */
        public List<AnggotaPojo> getData() {
            return data;
        }

        /**
         * @param data The data
         */
        public void setData(List<AnggotaPojo> data) {
            this.data = data;
        }
    }
}
