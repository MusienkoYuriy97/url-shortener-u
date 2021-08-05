package by.solbegsoft.shortener.demo.repository;

import by.solbegsoft.shortener.demo.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> getByShortUrl(String shortUrl);
    List<Url> getAllByUserUuid(UUID userUuid);
}