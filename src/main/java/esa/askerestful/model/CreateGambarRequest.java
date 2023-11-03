package esa.askerestful.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.websocket.OnOpen;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateGambarRequest {

    @NotBlank
    @Size(max = 100)
    private String namaGambar;

    @NotBlank
    @Size(max = 100)
    private String path;

    @NotBlank
    @Size(max = 10)
    private String ext;

}
