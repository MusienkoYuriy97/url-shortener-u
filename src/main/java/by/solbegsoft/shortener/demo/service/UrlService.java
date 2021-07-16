package by.solbegsoft.shortener.demo.service;

import by.solbegsoft.shortener.demo.common.ProtocolChecker;
import by.solbegsoft.shortener.demo.common.StringGenerator;
import by.solbegsoft.shortener.demo.exception.ShortUrlNotFoundException;
import by.solbegsoft.shortener.demo.model.Url;
import by.solbegsoft.shortener.demo.dto.UrlCreateDto;
import by.solbegsoft.shortener.demo.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UrlService {
    private UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        log.debug("UrlRepository " + urlRepository);
        this.urlRepository = urlRepository;
    }

    public String getOriginUrlByShortUrl(String shortUrl){
        log.debug("Method getOriginUrlByShortUrl is started with param shortUrl=" + shortUrl);
        Optional<Url> byShortUrl = urlRepository.getByShortUrl(shortUrl);

        if (byShortUrl.isPresent()) {
            String originUrl = byShortUrl
                    .get()
                    .getOriginUrl();
            return originUrl;
        }else {
            log.warn("Short url" + shortUrl + " doesn't exist");
            throw new ShortUrlNotFoundException();
        }
    }

    public Url save(UrlCreateDto urlCreateDto) {
        urlCreateDto.setOriginUrl(ProtocolChecker.setPrefix(urlCreateDto.getOriginUrl()));
        Url url = new Url();
        url.setOriginUrl(urlCreateDto.getOriginUrl());
        url.setShortUrl(StringGenerator.generate(10));
        url = urlRepository.save(url);
        log.info("Successfully save url" + url);
        return url;
    }
}