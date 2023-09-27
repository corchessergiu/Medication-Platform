package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.UUID;

public class UserDataDTO extends RepresentationModel<UserDataDTO> {
    private UUID id;
    private  String role;
    private String nameUser;
    private Date birth_date;
    private String gender;
    private  String adress;
    private  String username;
    private String password;

    public UserDataDTO(){}
    public UserDataDTO(UUID id, String role, String nameUser, Date birth_date, String gender, String adress, String username, String password) {
        this.id = id;
        this.role = role;
        this.nameUser = nameUser;
        this.birth_date = birth_date;
        this.gender = gender;
        this.adress = adress;
        this.username = username;
        this.password = password;
    }

    public UserDataDTO( String role, String nameUser, Date birth_date, String gender, String adress, String username, String password) {
        this.role = role;
        this.nameUser = nameUser;
        this.birth_date = birth_date;
        this.gender = gender;
        this.adress = adress;
        this.username = username;
        this.password = password;
    }


    public void setId(UUID id) {
        this.id = id;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
