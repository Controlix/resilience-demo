package be.mbict.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@RestController
public class BookController {

    @GetMapping("/books")
    public List<Book> allBooks(@RequestParam(value = "wait", defaultValue = "0", required = false) long millis) throws InterruptedException {
        MILLISECONDS.sleep(millis);
        return List.of(
                        new Book("Stephen Hawking", "A Brief History of Time"),
                        new Book("Douglas Adams", "The Hitchhiker's Guide to the Galaxy"));
    }
}

record Book(String author, String title) {}