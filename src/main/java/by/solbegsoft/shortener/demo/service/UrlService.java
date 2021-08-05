package by.solbegsoft.shortener.demo.service;

import by.solbegsoft.shortener.demo.common.ProtocolChecker;
import by.solbegsoft.shortener.demo.common.StringGenerator;
import by.solbegsoft.shortener.demo.exception.ShortUrlNotFoundException;
import by.solbegsoft.shortener.demo.exception.UrlDataException;
import by.solbegsoft.shortener.demo.model.Url;
import by.solbegsoft.shortener.demo.dto.UrlCreateRequest;
import by.solbegsoft.shortener.demo.repository.UrlRepository;
import by.solbegsoft.shortener.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String getOriginUrlByShortUrl(String shortUrl){
        log.info("Get origin url by short url");
        if (shortUrl == null){
            log.error("Shor url cannot be null");
            throw new UrlDataException("Short url cannot be null");
        }
        Optional<Url> optionalUrl = urlRepository.getByShortUrl(shortUrl);
        if (optionalUrl.isPresent()) {
            return optionalUrl.get().getOriginUrl();
        }else {
            log.error("Short url {} doesn't exist", shortUrl);
            throw new ShortUrlNotFoundException();
        }
    }

    public Url save(UrlCreateRequest urlCreateRequest, HttpServletRequest request) {
        log.info("Create short url by origin url");
        String userUuid = jwtTokenProvider.getUuid(request.getHeader("Authorization"));
        urlCreateRequest.setOriginUrl(ProtocolChecker.setPrefix(urlCreateRequest.getOriginUrl()));
        Url url = new Url();
        url.setOriginUrl(urlCreateRequest.getOriginUrl());
        url.setShortUrl(StringGenerator.generate(10));
        url.setUserUuid(UUID.fromString(userUuid));
        return urlRepository.save(url);
    }

    public List<Url> getAllByUuid(HttpServletRequest request) {
        String userUuid = jwtTokenProvider.getUuid(request.getHeader("Authorization"));
        log.info("Get url list");
        if (userUuid == null){
            throw new RuntimeException("");
        }
        return urlRepository.getAllByUserUuid(UUID.fromString(userUuid));
    }
}