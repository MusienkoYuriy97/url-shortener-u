package by.solbegsoft.shortener.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Url {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String originUrl;
    private String shortUrl;
}