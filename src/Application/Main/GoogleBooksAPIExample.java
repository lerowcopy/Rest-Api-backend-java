package Application.Main;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;

import java.util.List;

public class GoogleBooksAPIExample {

    private static final String APPLICATION_NAME = "YourApplicationName";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static void main(String[] args) {
        try {
            final Books books = new Books.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JSON_FACTORY,
                    null)
                    .setApplicationName(APPLICATION_NAME)
                    .setBooksRequestInitializer(new BooksRequestInitializer("AIzaSyBsQm00QtMgANvXCqO8UR8r25pSyhae8yg")) // Replace YOUR_API_KEY with your actual API key
                    .build();

            String query = "Психологий влияния"; // ваш запрос

            Books.Volumes.List list = books.volumes().list(query);

            Volumes volumes = list.execute();

            if (volumes != null && volumes.getTotalItems() != null) {
                System.out.println("Total items: " + volumes.getTotalItems());
                // Вывод информации о книгах
                volumes.getItems().forEach(volume -> System.out.println(volume.getVolumeInfo().getTitle()));
            } else {
                System.out.println("No results.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
