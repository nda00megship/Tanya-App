package esa.askerestful.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "follow")
public class Follow {

    @Id
    @Column(name = "id_following")
    private String idFollowing;

    @ManyToOne
    @JoinColumn(name = "follower_id_user", referencedColumnName = "id_user")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followed_id_user", referencedColumnName = "id_user")
    private User followed;

}
