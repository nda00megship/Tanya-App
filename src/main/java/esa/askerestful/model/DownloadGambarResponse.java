package esa.askerestful.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DownloadGambarResponse <T>{
    private T data;
    private HttpStatus status;
    private HttpHeaders headers;
}

