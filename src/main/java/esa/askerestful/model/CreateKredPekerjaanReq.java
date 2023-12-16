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
public class CreateKredPekerjaanReq {

    @NotBlank
    @Size(max = 50)
    public String posisi;

    @NotBlank
    @Size(max = 100)
    public String perusahaan;

    @NotBlank
    @Size(max = 50)
    public Date tahunMulai;

    @NotBlank
    @Size(max = 50)
    public Date tahunSelesai;
}
