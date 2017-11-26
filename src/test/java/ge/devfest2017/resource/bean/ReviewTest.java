/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.devfest2017.resource.bean;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

/**
 *
 * @author Amber
 */
public class ReviewTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesReviewToJSON() throws Exception {
        final Review reveiw = new Review("test@gmail", "მშვენიერია!", 5);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/review.json"), Review.class));

        assertThat(MAPPER.writeValueAsString(reveiw)).isEqualTo(expected);
    }
    
    @Test
    public void deserializesReviewFromJSON() throws Exception {
        final Review review = new Review("test@gmail", "მშვენიერია!", 5);
        assertThat(MAPPER.readValue(fixture("fixtures/review.json"), Review.class))
                .isEqualTo(review);
    }
}


