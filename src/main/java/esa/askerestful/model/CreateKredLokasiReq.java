package esa.askerestful.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateKredLokasiReq {

    @NotBlank
    @Size(max = 200)
    private String lokasi;

    @NotBlank
    @Size(max = 100)
    private Date tahunMulai;

    @NotBlank
    @Size(max = 100)
    private Date tahunSelesai;
}
