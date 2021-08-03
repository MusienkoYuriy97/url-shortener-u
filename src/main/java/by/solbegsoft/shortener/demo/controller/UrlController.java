package by.solbegsoft.shortener.demo.controller;

import by.solbegsoft.shortener.demo.dto.UrlCreateRequest;
import by.solbegsoft.shortener.demo.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/")
public class UrlController {
    private UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortUrlKey}")
    public ResponseEntity<?> redirect(@PathVariable String shortUrlKey){
        String redirectUrl = urlService.getOriginUrlByShortUrl(shortUrlKey);
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(redirectUrl))
                .build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UrlCreateRequest request){
        return new ResponseEntity<>(urlService.save(request),
                HttpStatus.CREATED);
    }
}