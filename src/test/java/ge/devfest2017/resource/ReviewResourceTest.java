package ge.devfest2017.resource;

import ge.devfest2017.resource.bean.Review;
import ge.devfest2017.resource.bean.Stat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author elle
 */
public class ReviewResourceTest extends ResourceTest {
    
    
    @Test
    public void testGenerateStats() {
        ArrayList<Review> reviews = new ArrayList<>();
        reviews.add(new Review("vinme@gmail.com", "მშვენიერია!", 5));
        reviews.add(new Review("vinme2@gmail.com", "უბრალოდ რეიტინგს ვაფუჭებ", 3));
        reviews.add(new Review("vinme3@gmail.com", "რეიტინგის გაფუჭებაში ვეხმარები", 1));
        
        Stat stats = ReviewResource.generateStats(reviews);
        assertEquals(5, stats.getMaxRating());
        assertEquals(1, stats.getMinRating());
        assertEquals(3, stats.getReviewCount());
        assertEquals(3, stats.getRating(), 0.0001);
    }
     
    
    private int submitReview(Review review) {
        Entity<Review> reviewEntity = Entity.entity(review, MediaType.APPLICATION_JSON_TYPE);
        return target("reviews").request()
                .post(reviewEntity)
                .readEntity(Integer.class);
    }
    
    private List<Review> getAllReviews() {
        return target("reviews").request()
                .get(new GenericType<List<Review>>(){});
    }
    
    private Review getById(int id) {
        return target("reviews/" + id).request()
                .get()
                .readEntity(Review.class);
    }
    
    private Stat getStat() {
        return target("reviews/stats").request()
                .get()
                .readEntity(Stat.class);
    }
    
    // Acceptance test
    @Test
    public void addAndCalculateRating() {
        
        // Submit review
        Review newReview = new Review("test@email", "Great event!", 5);
        int id = submitReview(newReview);
        
        // Get all reviews
        List<Review> reviews = getAllReviews();
        assertNotEquals("Review list is empty after adding review", 0, reviews.size());
        
        // Get by id
        Review reviewById = getById(id);
        assertEquals("Saved review does not match the local one when retrieving it from server by id", newReview, reviewById);
        
        
        Stat stat = getStat();
        assertEquals("Rating is not calculated correctly", 5, stat.getRating(), 0.001);
        assertEquals("Total review count is not calculated correctly", 1, stat.getReviewCount());
        assertEquals("Max rating is not calculated correctly", 5, stat.getMaxRating());
        assertEquals("Min rating is not calculated correctly", 5, stat.getMinRating());
        
    }
    
    @Test
    public void testNotFound() {
        Response response = target("reviews/1234")
                .request()
                .get();

        assertEquals(404, response.getStatus());
    }
}
