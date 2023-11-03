package esa.askerestful.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id_user")
    private String idUser;

    private String username;

    private String password;

    private String email;

    private String token;

    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;

    @OneToMany(mappedBy = "user")
    private List<Pertanyaan> pertanyaan;

    @OneToMany(mappedBy = "user")
    private List<Komentar> komentar;

    @OneToMany(mappedBy = "user")
    private List<Gambar> gambar;

}
