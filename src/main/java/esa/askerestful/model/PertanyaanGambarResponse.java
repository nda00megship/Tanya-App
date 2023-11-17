package esa.askerestful.model;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PertanyaanGambarResponse {

    private String id;
    private String username;
    private String header;
    private String deskripsi;
    private String tanggal;
    private Integer suka;
    private List<String> gambar;
    private Long totalKomentar;
    private List<KomentarResponseL> komentar;

}
