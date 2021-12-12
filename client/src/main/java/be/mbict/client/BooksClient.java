package be.mbict.client;

import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

@FeignClient("books")
public interface BooksClient {

    @TimeLimiter(name = "books", fallbackMethod = "noBooks")
    default CompletableFuture<List<?>> timeLimitedBooks(long millis) {
        return CompletableFuture.supplyAsync(() -> allBooks(millis));
    }

    @GetMapping("/books")
    List<?> allBooks(@RequestParam(value = "wait", defaultValue = "0", required = false) long millis);

    default CompletableFuture<List<?>> noBooks(long millis, TimeoutException ex) {
        return CompletableFuture.completedFuture(Collections.emptyList());
    }
}
