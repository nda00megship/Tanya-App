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
    public String idKredensial;

    public String Posisi;

    public String perusahaan;

    @Column(name = "tahun_mulai")
    public Date tahunMulai;

    @Column(name = "tahun_selesai")
    public Date tahunSelesai;

    @ManyToOne
    @JoinColumn(name = "idUser" , referencedColumnName = "id_user")
    public User user;
}
