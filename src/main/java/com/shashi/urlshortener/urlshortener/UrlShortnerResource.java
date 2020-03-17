package com.shashi.urlshortener.urlshortener;

import com.google.common.hash.Hashing;
import com.shashi.urlshortener.urlshortener.model.Shortener;
import com.shashi.urlshortener.urlshortener.repository.ShortnerRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/urlShortner")
@RestController
public class UrlShortnerResource {

    private static final String DOMAIN_URL = "https://y.hi/";
    private final ShortnerRepository shortnerRepository;

    public UrlShortnerResource(ShortnerRepository shortnerRepository) {
        this.shortnerRepository = shortnerRepository;
    }

    @GetMapping("/{id}")
    public Shortener getUrl(@PathVariable String id) {
        return shortnerRepository.findByShortUrl(id);
    }

    @GetMapping()
    public List<Shortener> getAll() {
        return (List<Shortener>) shortnerRepository.findAll();
    }

    @PostMapping
    public String create(@RequestBody String url) {
        UrlValidator urlValidator = new UrlValidator(new String[] { "https", "http" });
        if (urlValidator.isValid(url)) {
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            if (!checkUrlExists(id)) {
                return create(id, url);
            }
            return DOMAIN_URL + id;

        }
        throw new IllegalStateException("Url is invalid " + url);
    }

    public String create(String id, String longUrl) {
        Shortener shortener = new Shortener();
        shortener.setCreatedDate(LocalDateTime.now());
        shortener.setExpireDate(LocalDateTime.now().plusYears(1));
        shortener.setLongUrl(longUrl);
        shortener.setShortUrl(id);
        shortnerRepository.save(shortener);
        return DOMAIN_URL + shortener.getShortUrl();
    }

    public boolean checkUrlExists(String id) {
        Shortener existing = shortnerRepository.findByShortUrl(id);
        if (existing != null) {
            return existing.getShortUrl().equals(id);
        }
        return false;
    }
}
