package by.solbegsoft.shortener.demo.controller;

import by.solbegsoft.shortener.demo.dto.UrlCreateRequest;
import by.solbegsoft.shortener.demo.service.UrlService;
import by.solbegsoft.shortener.demo.swagger.ApiGetAllUrl;
import by.solbegsoft.shortener.demo.swagger.ApiGetRedirectUrl;
import by.solbegsoft.shortener.demo.swagger.ApiPostSaveUrl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
@Tag(name = "UrlController", description = "End points for url create and get")
public class UrlController {
    private final UrlService urlService;

    @GetMapping("/redirect/{shortUrlKey}")
    @ApiGetRedirectUrl
    public ResponseEntity<?> redirect(@PathVariable String shortUrlKey){
        String redirectUrl = urlService.getOriginUrlByShortUrl(shortUrlKey);
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(redirectUrl))
                .build();
    }

    @GetMapping("/get-all")
    @ApiGetAllUrl
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(urlService.getAllByUuid(),
                HttpStatus.OK);
    }

    @PostMapping("/save")
    @ApiPostSaveUrl
    public ResponseEntity<?> save(@Valid @RequestBody UrlCreateRequest urlCreateRequest){
        return new ResponseEntity<>(urlService.save(urlCreateRequest),
                HttpStatus.CREATED);
    }
}