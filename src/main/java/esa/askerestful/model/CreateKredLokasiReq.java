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
    public String lokasi;

    @NotBlank
    @Size(max = 100)
    public Date tahunMulai;

    @NotBlank
    @Size(max = 100)
    public Date tahunSelesai;
}
