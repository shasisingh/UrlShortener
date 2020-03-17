package com.shashi.urlshortener.urlshortener.repository;

import com.shashi.urlshortener.urlshortener.model.Shortener;
import org.springframework.data.repository.CrudRepository;

public interface ShortnerRepository extends CrudRepository<Shortener, Long> {
    Shortener findByShortUrl(String shortUrl);

}
