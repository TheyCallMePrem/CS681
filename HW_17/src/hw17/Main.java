package hw17;
public class Main {
    public static void main(String[] args) {
        // Create some movies
        Revised_Movie movie1 = new Revised_Movie("Ikiru (1952)", 2);
        Revised_Movie movie2 = new Revised_Movie("One Piece Film: Red (2022)", 1);
        Revised_Movie movie3 = new Revised_Movie("Harakiri (1962)", 3);
        
        // Create some customers
        Revised_Customer customer1 = new Revised_Customer();
        Revised_Customer customer2 = new Revised_Customer();
        Revised_Customer customer3 = new Revised_Customer();
        
        // Simulate renting and returning movies
        customer1.rentMovie(movie1);
        customer2.rentMovie(movie2);
        customer3.rentMovie(movie3);
        
        customer1.returnMovie();
        customer2.returnMovie();
        customer3.returnMovie();
        
        customer1.rentMovie(movie3);
        customer2.rentMovie(movie1);
        
        customer1.returnMovie();
        customer2.returnMovie();
        
        customer3.rentMovie(movie2);
        
        // Print the available copies of each movie
        System.out.println(movie1.getTitle() + " - Available copies: " + movie1.getAvailableCopies());
        System.out.println(movie2.getTitle() + " - Available copies: " + movie2.getAvailableCopies());
        System.out.println(movie3.getTitle() + " - Available copies: " + movie3.getAvailableCopies());
    }
}

