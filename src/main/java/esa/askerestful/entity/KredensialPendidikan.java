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
    public String idKredensialPendidikan;

    public String sekolah;

    public String jurusan;

    @Column(name = "jenis_gelar")
    public String jenisGelar;

    @Column(name = "tahun_kelulusan")
    public Date tahunKelulusan;

    @ManyToOne
    @JoinColumn(name = "idUser" , referencedColumnName = "id_user")
    public User user;
}
