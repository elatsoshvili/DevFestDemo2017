package ge.devfest2017.dao;

import ge.devfest2017.resource.bean.Review;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 *
 * @author elle
 */

public interface ReviewDAO {
    @SqlUpdate("INSERT INTO reviews(email, review, stars) VALUES (?, ?, ?)")
    @GetGeneratedKeys
    int insert(String email, String review, int stars);

    @SqlUpdate("INSERT INTO reviews(email, review, stars) VALUES (:email, :review, :stars)")
    @GetGeneratedKeys
    int insert(@BindBean Review review);

    @SqlQuery("SELECT * FROM reviews")
    @RegisterMapper(ReviewMapper.class)
    List<Review> listReviews();
    
    @SqlQuery("SELECT * FROM reviews where id = :id")
    @RegisterMapper(ReviewMapper.class)
    Review getById(@Bind("id") int id);
}