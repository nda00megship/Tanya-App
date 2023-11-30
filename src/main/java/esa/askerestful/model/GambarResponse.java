package esa.askerestful.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GambarResponse {

    private String namaGambar;

    private String path;

    private Timestamp tanggal;
}
