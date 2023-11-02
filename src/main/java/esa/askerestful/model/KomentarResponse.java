package esa.askerestful.model;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class KomentarResponse {
    private String id_komentar;

    private String id_pertanyaan;

    private String deskripsi;

    private Timestamp tanggal;
}
