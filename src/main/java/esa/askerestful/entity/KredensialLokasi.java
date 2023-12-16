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
@Table(name = "kredensial_lokasi")
public class KredensialLokasi {

    @Id
    private String idKredensialLokasi;

    private String lokasi;

    @Column(name = "tahun_mulai")
    private Date tahunMulai;

    @Column(name = "tahun_selesai")
    private Date tahunSelesai;

    @ManyToOne
    @JoinColumn(name = "idUser" , referencedColumnName = "id_user")
    private User user;
}
