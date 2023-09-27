package ro.tuc.ds2020.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
@Table (name="doctor")
public class Doctor extends UserData implements Serializable {




    public Doctor(){}

    public Doctor(UUID id, String username, String password, String role, String nameUser, Date birth_date, String gender, String adress) {
        super(id, username, password, role, nameUser, birth_date, gender, adress);
    }

    public Doctor(String username, String password, String role, String nameUser, Date birth_date, String gender, String adress) {
        super(username, password, role, nameUser, birth_date, gender, adress);
    }


}
