package be.mbict.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class RetryController {

    private final MovieClient movieClient;

    public RetryController(MovieClient movieClient) {
        this.movieClient = movieClient;
    }

    @GetMapping("/resilient/retry")
    public List<?> timeLimited(@RequestParam(name = "failures", defaultValue = "0", required = false) long failures) throws ExecutionException, InterruptedException {
        return movieClient.allMovies(failures);
    }
}
