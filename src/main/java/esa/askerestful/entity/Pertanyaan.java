package esa.askerestful.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pertanyaan")
public class Pertanyaan {

    @Id
    @Column(name = "id_pertanyaan")
    private String idPertanyaan;

    private String header;

    private String deskripsi;

    private int suka;

    private Timestamp tanggal;

    @OneToMany(mappedBy = "pertanyaan")
    private List<Gambar> gambar;

    @ManyToOne
    @JoinColumn(name = "idUser" , referencedColumnName = "id_user")
    private User user;

    @OneToMany(mappedBy = "pertanyaan")
    private List<Komentar> komentar;
}
