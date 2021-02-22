//package nl.eindopdracht.bootcamp.model;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.Lob;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.hibernate.annotations.GenericGenerator;
//
//@Entity
//@Table
//public class FileDB {
//
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    private String id;
//
//    private String name;
//
//    private String type;
//
//    @JsonIgnore
//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval  = true)
//    @JoinColumn(name = "appuser_id", referencedColumnName = "id")
//    private AppUser appuser;
//
////    @JsonIgnore
////    @OneToOne
////    @JoinColumn(name = "appuser_id", referencedColumnName = "id")
////    private AppUser appUser;
//
//    @Lob
//    private byte[] data;
//
//    public FileDB() {
//    }
//
//    public FileDB(String name, String type, byte[] data) {
//        this.name = name;
//        this.type = type;
//        this.data = data;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public byte[] getData() {
//        return data;
//    }
//
//    public void setData(byte[] data) {
//        this.data = data;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public AppUser getAppuser() {
//        return appuser;
//    }
//
//    public void setAppuser(AppUser appuser) {
//        this.appuser = appuser;
//    }
//}
