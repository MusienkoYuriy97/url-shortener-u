package by.solbegsoft.shortener.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CustomErrorResponse {
    private String error;
    private int status;
    private LocalDateTime timestamp;
}
