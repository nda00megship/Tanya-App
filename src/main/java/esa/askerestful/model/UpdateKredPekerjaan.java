package esa.askerestful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateKredPekerjaan {

    @JsonIgnore
    private String idKredPekerjaan;
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
