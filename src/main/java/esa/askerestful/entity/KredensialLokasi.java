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
    public String idLokasi;

    public String lokasi;

    @Column(name = "tahun_mulai")
    public Date tahunMulai;

    @Column(name = "tahun_selesai")
    public Date tahunSelesai;

    @ManyToOne
    @JoinColumn(name = "idUser" , referencedColumnName = "id_user")
    public User user;
}
