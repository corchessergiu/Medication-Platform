package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;
import java.util.UUID;

@Entity
@Table (name="user_data")
@Inheritance(strategy =  InheritanceType.TABLE_PER_CLASS)
public class UserData implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "nameUser", nullable = false)
    private String nameUser;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth_date;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "adress", nullable = false)
    private String adress;


    public UserData() {
    }

    public UserData(UUID id, String username, String password, String role, String nameUser, Date birth_date, String gender, String adress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.nameUser = nameUser;
        this.birth_date = birth_date;
        this.gender = gender;
        this.adress = adress;
    }

    public UserData(String username, String password, String role, String nameUser, Date birth_date, String gender, String adress) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.nameUser = nameUser;
        this.birth_date = birth_date;
        this.gender = gender;
        this.adress = adress;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getNameUser() {
        return nameUser;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public String getGender() {
        return gender;
    }

    public String getAdress() {
        return adress;
    }
}
