package by.solbegsoft.shortener.demo.controller;

import by.solbegsoft.shortener.demo.dto.UrlCreateRequest;
import by.solbegsoft.shortener.demo.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @GetMapping("/redirect/{shortUrlKey}")
    public ResponseEntity<?> redirect(@PathVariable String shortUrlKey){
        String redirectUrl = urlService.getOriginUrlByShortUrl(shortUrlKey);
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(redirectUrl))
                .build();
    }

    @GetMapping("/getall/{userUuid}")
    public ResponseEntity<?> getAll(@PathVariable String userUuid){
        return new ResponseEntity<>(urlService.getAllByUuid(UUID.fromString(userUuid)),
                HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody UrlCreateRequest request){
        return new ResponseEntity<>(urlService.save(request),
                HttpStatus.CREATED);
    }
}