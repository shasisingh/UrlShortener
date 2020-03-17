package com.shashi.urlshortener.urlshortener.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Entity(name = "SHORTENER")
public class Shortener {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROCESSED_FILE")
    @SequenceGenerator(name = "SEQ_SHORTNER", sequenceName = "SEQ_SHORTNER_URL", allocationSize = 1)
    private long id;
    private String longUrl;
    private String shortUrl;
    private LocalDateTime createdDate;
    private LocalDateTime expireDate;

    public Shortener() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "Shortner{" + "id=" + id + ", longUrl='" + longUrl + '\'' + ", shortUrl='" + shortUrl + '\'' + ", createdDate=" + createdDate
                + ", expireDate=" + expireDate + '}';
    }

}
