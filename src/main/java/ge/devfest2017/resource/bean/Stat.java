package ge.devfest2017.resource.bean;

/**
 *
 * @author elle
 */
public class Stat {
    private int maxRating;
    private int minRating;
    private double rating;
    private int reviewCount;
    
    public Stat(){}
    public Stat(int maxRating, int minRating, double rating, int reviewCount) {
        this.maxRating = maxRating;
        this.minRating = minRating;
        this.rating = rating;
        this.reviewCount = reviewCount;
    }

    public int getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(int maxRating) {
        this.maxRating = maxRating;
    }

    public int getMinRating() {
        return minRating;
    }

    public void setMinRating(int minRating) {
        this.minRating = minRating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
    
    
}
