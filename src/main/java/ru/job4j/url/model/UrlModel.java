package ru.job4j.url.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс UrlModel
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Entity
@Table(name = "urls")
public class UrlModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    private String code;
    private int siteId;
    private int total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UrlModel urlModel = (UrlModel) o;
        return id == urlModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
