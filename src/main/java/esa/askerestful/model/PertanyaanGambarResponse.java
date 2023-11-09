package esa.askerestful.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PertanyaanGambarResponse {


    private String id;

    private String header;

    private String deskripsi;

    private Integer suka;

    private Timestamp tanggal;

    private GambarObjectResponse idGambar;

}
