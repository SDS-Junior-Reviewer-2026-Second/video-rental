package com.videorental;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {
    public static final String NAME = "NAME_NOT_IMPORTANT";
    public static final String TITLE = "TITLE_NOT_IMPORTANT";
    Customer customer = new Customer(NAME);

    @Test
    public void returnNewCustomer() {
        assertThat(customer).isNotNull();
    }

    @Test
    public void statementForNoRental() {
        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "Amount owed is 0.0\n"
                + "You earned 0 frequent renter pointers");
    }

    @Test
    public void statementForRegularMovieRentalForLessThan3Days() {
        // arrange
        customer.addRental(createRentalFor(2, Movie.REGULAR));

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t2.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 2.0\n"
                + "You earned 1 frequent renter pointers");
    }

    private static Rental createRentalFor(int daysRented, int priceCode) {
        Movie movie = new Movie(TITLE, priceCode);
        Rental rental = new Rental(movie, daysRented);
        return rental;
    }

    @Test
    public void statementForRegularMovieRentalForMoreThan2Days() {
        // arrange
        customer.addRental(createRentalFor(3, Movie.REGULAR));

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.5\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForNewReleaseMovie() {
        // arrange
        customer.addRental(createRentalFor(1, Movie.NEW_RELEASE));

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForChildrensMovieRentalMoreThan3Days() {
        // arrange
        customer.addRental(createRentalFor(4, Movie.CHILDRENS));

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForChildrensMovieRentalLessThan4Days() {
        // arrange
        customer.addRental(createRentalFor(3, Movie.CHILDRENS));

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t1.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 1.5\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForNewReleaseMovieRentalMoreThan1Day() {
        // arrange
        customer.addRental(createRentalFor(2, Movie.NEW_RELEASE));

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t6.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 6.0\n"
                + "You earned 2 frequent renter pointers");
    }

    @Test
    public void statementForUnknownPriceCode() {
        // arrange
        int unknownPriceCode = 99;
        customer.addRental(createRentalFor(2, unknownPriceCode));

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t0.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 0.0\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForFewMovieRental() {
        Movie regularMovie = new Movie(TITLE, Movie.REGULAR);
        Movie newReleaseMovie = new Movie(TITLE, Movie.NEW_RELEASE);
        Movie childrensMovie = new Movie(TITLE, Movie.CHILDRENS);
        customer.addRental(new Rental(regularMovie, 1));
        customer.addRental(new Rental(newReleaseMovie, 4));
        customer.addRental(new Rental(childrensMovie, 4));
        String statement = customer.statement();
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t2.0(TITLE_NOT_IMPORTANT)\n"
                + "\t12.0(TITLE_NOT_IMPORTANT)\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 17.0\n"
                + "You earned 4 frequent renter pointers");
    }

    @Test
    public void returnNewCustomer() {
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        assertThat(customer).isNotNull();
    }

    @Test
    public void statementForNoRental() {
        // arrange
        Customer customer = new Customer("NAME_NOT_IMPORTANT");

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "Amount owed is 0.0\n"
                + "You earned 0 frequent renter pointers");
    }

    @Test
    public void statementForRegularMovieRentalForLessThan3Days() {
        // arrange
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
        int daysRented = 2;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t2.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 2.0\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForRegularMovieRentalForMoreThan2Days() {
        // arrange
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
        int daysRented = 3;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.5\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForNewReleaseMovie() {
        // arrange
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.NEW_RELEASE);
        int daysRented = 1;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForChildrensMovieRentalMoreThan3Days() {
        // arrange
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.CHILDRENS);
        int daysRented = 4;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForChildrensMovieRentalLessThan4Days() {
        // arrange
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.CHILDRENS);
        int daysRented = 3;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t1.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 1.5\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForNewReleaseMovieRentalMoreThan1Day() {
        // arrange
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.NEW_RELEASE);
        int daysRented = 2;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // act
        String statement = customer.statement();

        // assert
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t6.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 6.0\n"
                + "You earned 2 frequent renter pointers");
    }

    @Test
    public void statementForFewMovieRental() {
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie regularMovie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
        Movie newReleaseMovie = new Movie("TITLE_NOT_IMPORTANT", Movie.NEW_RELEASE);
        Movie childrensMovie = new Movie("TITLE_NOT_IMPORTANT", Movie.CHILDRENS);
        customer.addRental(new Rental(regularMovie, 1));
        customer.addRental(new Rental(newReleaseMovie, 4));
        customer.addRental(new Rental(childrensMovie, 4));
        String statement = customer.statement();
        assertThat(statement).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t2.0(TITLE_NOT_IMPORTANT)\n"
                + "\t12.0(TITLE_NOT_IMPORTANT)\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 17.0\n"
                + "You earned 4 frequent renter pointers");
    }
}
