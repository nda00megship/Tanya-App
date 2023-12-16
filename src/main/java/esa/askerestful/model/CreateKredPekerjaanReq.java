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
    private String posisi;

    @NotBlank
    @Size(max = 100)
    private String perusahaan;

    @NotBlank
    @Size(max = 50)
    private Date tahunMulai;

    @NotBlank
    @Size(max = 50)
    private Date tahunSelesai;
}
