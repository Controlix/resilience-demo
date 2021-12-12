package be.mbict.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@RestController
public class MovieController {

    private static long failuresLeft = 0L;
    private static long originalFailures = 0L;

    @GetMapping("/movies")
    public List<Movie> allMovies(@RequestParam(value = "failures", defaultValue = "0", required = false) long failures) throws InterruptedException {
        if (originalFailures != failures) {
            originalFailures = failures;
            failuresLeft = failures;
        }
        if (failuresLeft > 0) {
            failuresLeft--;
            throw new RuntimeException("Failed to get all movies");
        }
        return List.of(
                        new Movie("Steven Spielberg", "Indiana Jones and the Raiders of the Lost Ark"),
                        new Movie("George Lucas", "Star Wars: Episode IV - A New Hope"));
    }
}

record Movie(String director, String title) {}