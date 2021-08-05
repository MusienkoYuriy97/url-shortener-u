package by.solbegsoft.shortener.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "url")
@Schema(name = "Url")
public class Url {
    @Id
    @GeneratedValue
    @Column(name = "uuid", nullable = false)
    @Schema(example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID uuid;

    @Column(name = "origin_url", nullable = false)
    @Schema(example = "http://www.google.com")
    private String originUrl;

    @Column(name = "short_url", nullable = false)
    @Schema(example = "1234FDG")
    private String shortUrl;

    @Column(name = "user_uuid", nullable = false)
    @Schema(example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID userUuid;
}