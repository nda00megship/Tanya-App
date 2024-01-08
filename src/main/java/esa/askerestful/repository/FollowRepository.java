package esa.askerestful.repository;

import esa.askerestful.entity.Follow;
import esa.askerestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, String> {
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Follow f WHERE f.follower = :follower AND f.followed = :followed")
    boolean existsByFollowerAndFollowed(User follower, User followed);

    @Query("SELECT f FROM Follow f WHERE f.follower = :follower AND f.followed = :followed")
    Optional<Follow> findByFollowerAndFollowed(@Param("follower") User follower, @Param("followed") User followed);

    @Query("SELECT f.followed.username FROM Follow f WHERE f.follower.username = :followerUsername")
    Optional<List<String>> findFollowedUsernamesByFollowerUsername(@Param("followerUsername") String followerUsername);

}
