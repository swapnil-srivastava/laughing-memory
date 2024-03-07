package eu.swapnilsrivastava.swapnilsrivastava.model;

import java.sql.Types;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;

@Entity(name="speakers")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long speaker_id;
    private String first_name;
    private String last_name;
    private String title;
    private String company;
    private String speaker_bio;


    // @Type(type="org.hibernate.type.BinaryType") --> @JdbcTypeCode(Types.BINARY) Since hibernate 6
    @Lob
    @JdbcTypeCode(Types.BINARY)
    private byte[] speaker_photo;

    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore
    private List<Session> sessions;
    
    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public Speaker() {
    
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpeaker_bio() {
        return speaker_bio;
    }

    public void setSpeaker_bio(String speaker_bio) {
        this.speaker_bio = speaker_bio;
    }

    public byte[] getSpeaker_photo() {
        return speaker_photo;
    }

    public void setSpeaker_photo(byte[] speaker_photo) {
        this.speaker_photo = speaker_photo;
    }

}
