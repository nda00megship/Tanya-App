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
public class KredPendidikanResp {

    public String sekolah;

    public String jurusan;

    public String jenisGelar;

    public Date tahunLulus;
}
