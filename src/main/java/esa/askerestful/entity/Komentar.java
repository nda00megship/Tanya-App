package esa.askerestful.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "komentar")
public class Komentar {

    @Id
    private String idKomentar;

    private String deskripsi;

    private Timestamp tanggal;

    @ManyToOne
    @JoinColumn(name = "idPertanyaan" , referencedColumnName = "id_pertanyaan")
    private Pertanyaan pertanyaan;

    @ManyToOne
    @JoinColumn(name = "idUser" , referencedColumnName = "id_user")
    private User user;
}
