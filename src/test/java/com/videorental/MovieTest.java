package com.videorental;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieTest {
    private static final String TITLE = "TITLE_NOT_IMPORTANT";

    @Test
    public void createMovieWithTitleAndPriceCode() {
        Movie movie = new Movie(TITLE, Movie.REGULAR);

        assertThat(movie.getTitle()).isEqualTo(TITLE);
        assertThat(movie.getPriceCode()).isEqualTo(Movie.REGULAR);
    }

    @Test
    public void setPriceCodeChangesPriceCode() {
        Movie movie = new Movie(TITLE, Movie.REGULAR);

        movie.setPriceCode(Movie.NEW_RELEASE);

        assertThat(movie.getPriceCode()).isEqualTo(Movie.NEW_RELEASE);
    }
}
