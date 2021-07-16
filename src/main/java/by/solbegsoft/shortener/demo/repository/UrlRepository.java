package by.solbegsoft.shortener.demo.repository;

import by.solbegsoft.shortener.demo.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> getByShortUrl(String shortUrl);
}