package esa.askerestful.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "kredensial_pekerjaan")
public class KredensialPekerjaan {

    @Id
    private String idKredensialPekerjaan;

    private String Posisi;

    private String perusahaan;

    @Column(name = "tahun_mulai")
    private Date tahunMulai;

    @Column(name = "tahun_selesai")
    private Date tahunSelesai;

    @ManyToOne
    @JoinColumn(name = "idUser" , referencedColumnName = "id_user")
    private User user;
}
