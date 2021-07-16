package by.solbegsoft.shortener.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String originUrl;
    private String shortUrl;
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;

    public Url(String originUrl, String shortUrlKey) {
        this.originUrl = originUrl;
        this.shortUrl = shortUrlKey;
    }

    @PrePersist
    protected void date(){
        createdDate = LocalDateTime.now();
        expirationDate = createdDate.plusDays(10);
    }
}