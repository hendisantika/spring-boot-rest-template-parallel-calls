package id.my.hendisantika.parallelcalls.service;

import id.my.hendisantika.parallelcalls.mock.dto.MockResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-template-parallel-calls
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/10/24
 * Time: 17.26
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ParallelService {

    private final RestTemplate restTemplate;

    private final Executor executor = Executors.newFixedThreadPool(10);

    // Fetches data from a given URL asynchronously
    public CompletableFuture<MockResponse> fetchData(String url) {
        return CompletableFuture.supplyAsync(() -> restTemplate.getForObject(url, MockResponse.class), executor);
    }

    // Fetches data from multiple URLs in parallel and aggregates the results
    public CompletableFuture<List<MockResponse>> fetchAllData() {
        CompletableFuture<MockResponse> call1 = fetchData("http://localhost:8080/mock/data/1");
        CompletableFuture<MockResponse> call2 = fetchData("http://localhost:8080/mock/data/2");
        CompletableFuture<MockResponse> call3 = fetchData("http://localhost:8080/mock/data/3");

        return CompletableFuture.allOf(call1, call2, call3)
                .thenApply(v -> Stream.of(call1, call2, call3)
                        .map(CompletableFuture::join)
                        .filter(Objects::nonNull)
                        .toList());
    }

    // Fetches data from multiple URLs in parallel with a global timeout and aggregates the results
    public CompletableFuture<List<MockResponse>> fetchAllDataWithTimeout(long globalTimeout, long individualTaskTimeout) {
        CompletableFuture<MockResponse> call1 = fetchDataWithTimeout("http://localhost:8080/mock/data/1", individualTaskTimeout);
        CompletableFuture<MockResponse> call2 = fetchDataWithTimeout("http://localhost:8080/mock/data/2", individualTaskTimeout);
        CompletableFuture<MockResponse> call3 = fetchDataWithTimeout("http://localhost:8080/mock/data/3", individualTaskTimeout);

        return CompletableFuture.allOf(call1, call2, call3)
                .orTimeout(globalTimeout, TimeUnit.MILLISECONDS)
                .handle((result, ex) -> {
                    if (ex != null) {
                        if (ex instanceof TimeoutException) {
                            log.error("Global timeout exception encountered");
                        } else {
                            log.error("Some other exception encountered at global level");
                        }
                        return Collections.emptyList();
                    }
                    return Stream.of(call1, call2, call3)
                            .map(CompletableFuture::join)
                            .filter(Objects::nonNull)
                            .toList();
                });
    }
}
