package ru.job4j.url.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс SiteModel
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Entity
@Table(name = "sites")
public class SiteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String site;
    private String login;
    private String password;

    @Transient
    private boolean registration;

    public static SiteModel of(int id,
                               String site,
                               String login,
                               String password,
                               boolean registration) {
        SiteModel siteModel = new SiteModel();
        siteModel.id = id;
        siteModel.site = site;
        siteModel.login = login;
        siteModel.password = password;
        siteModel.registration = registration;
        return siteModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SiteModel siteModel = (SiteModel) o;
        return id == siteModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
