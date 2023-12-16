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
public class CreateKredPendidikanReq {

    @NotBlank
    @Size(max = 100)
    private String sekolah;

    @NotBlank
    @Size(max = 100)
    private String jurusan;

    @NotBlank
    @Size(max = 50)
    private String jenisGelar;

    @NotBlank
    private Date tahunLulus;
}
