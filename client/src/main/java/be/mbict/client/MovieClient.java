package be.mbict.client;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("movies")
public interface MovieClient {

    @Retry(name = "movies")
    @GetMapping("/movies")
    public List<?> allMovies(@RequestParam(value = "failures", defaultValue = "0", required = false) long failures);
}
