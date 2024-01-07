package esa.askerestful.repository;

import esa.askerestful.entity.Follow;
import esa.askerestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, String> {
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Follow f WHERE f.follower = :follower AND f.followed = :followed")
    boolean existsByFollowerAndFollowed(User follower, User followed);
}
