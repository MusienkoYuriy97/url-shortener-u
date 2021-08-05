package by.solbegsoft.shortener.demo.swagger;

import by.solbegsoft.shortener.demo.model.Url;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Save url")
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Url.class))),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content)})
public @interface ApiPostSaveUrl {
}
