package ge.devfest2017.resource.bean;

import java.util.Objects;

/**
 *
 * @author elle
 */
public class Review {
    private static final int MIN_STARS = 0;
    private static final int MAX_STARS = 5;
    
    private int id;
    private String email;
    private String review;
    private int stars;
    
    public Review(){}
    public Review(String email, String review, int stars) {
        this.email = email;
        this.review = review;
        this.stars = stars;
    }
    
    public Review(int id, String email, String review, int stars) {
        this(email, review, stars);
        this.id = id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Review)) return false;
        Review o = (Review) obj;
        return (this == obj 
                || (this.email.equals(o.getEmail())
                    && this.review.equals(o.getReview())
                    && this.stars == o.getStars()));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.email);
        hash = 31 * hash + Objects.hashCode(this.review);
        hash = 31 * hash + this.stars;
        return hash;
    }
    
    @Override
    public String toString(){
        return String.format("[%s, %s, %d]", email, review, stars);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
    
    public boolean hasValidData() {
        return stars >= MIN_STARS && stars <= MAX_STARS;
    }
}
