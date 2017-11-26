package ge.devfest2017.dao;

import ge.devfest2017.resource.bean.Review;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 *
 * @author elle
 */
public class ReviewMapper implements ResultSetMapper<Review> {

    @Override
    public Review map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Review(r.getInt("id"), r.getString("email"), r.getString("review"), r.getInt("stars"));
    }

}
