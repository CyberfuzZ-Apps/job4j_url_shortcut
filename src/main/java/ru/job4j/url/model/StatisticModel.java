package ru.job4j.url.model;

import java.util.Objects;

/**
 * Класс StatisticModel
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class StatisticModel {

    private String url;
    private int total;

    public StatisticModel() {
    }

    public StatisticModel(String url, int total) {
        this.url = url;
        this.total = total;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        StatisticModel that = (StatisticModel) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
