package esa.askerestful.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class KredPekerjaanResp {

    public String posisi;

    public String perusahaan;

    public Date tahunMulai;

    public Date tahunSelesai;
}
