package esa.askerestful.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "kredensial_pendidikan")
public class KredensialPendidikan {

    @Id
    private String idKredensialPendidikan;

    private String sekolah;

    private String jurusan;

    @Column(name = "jenis_gelar")
    private String jenisGelar;

    @Column(name = "tahun_kelulusan")
    private Date tahunKelulusan;

    @ManyToOne
    @JoinColumn(name = "idUser" , referencedColumnName = "id_user")
    private User user;
}
