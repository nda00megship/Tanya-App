package esa.askerestful.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "store_gambar")
public class Gambar {

    @Id
    @Column(name = "id_gambar")
    private String idGambar;

    @Column(name = "nama_gambar")
    private String namaGambar;

    private String path;

    private String ext;

    private Timestamp tanggal;

    @ManyToOne
    @JoinColumn(name = "idUser" , referencedColumnName = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idPertanyaan" , referencedColumnName = "id_pertanyaan")
    private Pertanyaan pertanyaan;
}
