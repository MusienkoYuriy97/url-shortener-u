package by.solbegsoft.shortener.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "origin_url", nullable = false)
    private String originUrl;

    @Column(name = "short_url", nullable = false)
    private String shortUrl;

    @Column(name = "user_uuid", nullable = false)
    private UUID userUuid;
}