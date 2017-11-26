package ge.devfest2017.resource;

import ge.devfest2017.dao.ReviewDAO;
import ge.devfest2017.resource.bean.Review;
import ge.devfest2017.resource.bean.Stat;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author elle
 */
@Path("reviews")
public class ReviewResource {
    static final Logger LOG = LoggerFactory.getLogger(ReviewResource.class);
    private final ReviewDAO reviewDAO;

    public ReviewResource(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReview(Review review) {
        if (review.hasValidData()) {
            int id = reviewDAO.insert(review);
            return Response.ok(id).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @GET
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> reviews(){
        return reviewDAO.listReviews();
    }
    
    /**
     *
     * @param id Review id
     * @return
     */
    @GET
    @Path("/{id}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id){
        Review review = reviewDAO.getById(id);
        if (review == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(review).build();
        }
    }
   
    @GET
    @Path("/stats")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Stat stats(){
        return generateStats(reviewDAO.listReviews());
    }
    
    public static Stat generateStats(List<Review> reviews){
        int count = reviews.size();
        double rating;
        double sum = 0;
        int min = 5;
        int max = 0;
        for (int i = 0; i < count; i++ ) {
            int stars = reviews.get(i).getStars();
            sum += stars;
            min = Math.min(min, stars);
            max = Math.max(max, stars);
        }
        
        if (!reviews.isEmpty()) {
            rating = sum / count;
        } else {
            rating = 0;
        }
        
        return new Stat(max, min, rating, count);
    }
}