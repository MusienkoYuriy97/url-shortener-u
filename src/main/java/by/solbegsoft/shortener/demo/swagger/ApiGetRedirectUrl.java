package by.solbegsoft.shortener.demo.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Redirect")
@ApiResponses(value = {
        @ApiResponse(responseCode = "301", description = "MOVED_PERMANENTLY", content = @Content(mediaType = "text/html")),
        @ApiResponse(responseCode = "400", description = "Short url cannot be null", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
@SecurityRequirements
public @interface ApiGetRedirectUrl {
}
