package be.mbict.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class TimeLimitController {

    private final BooksClient timeLilimClient;

    public TimeLimitController(BooksClient timeLilimClient) {
        this.timeLilimClient = timeLilimClient;
    }

    @GetMapping("/resilient/timelimit")
    public List<?> timeLimited(@RequestParam(name = "wait", defaultValue = "0", required = false) long wait) throws ExecutionException, InterruptedException {
        return timeLilimClient.timeLimitedBooks(wait).get();
    }
}
