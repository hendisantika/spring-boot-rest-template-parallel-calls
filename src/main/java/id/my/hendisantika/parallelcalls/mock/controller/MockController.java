package id.my.hendisantika.parallelcalls.mock.controller;

import id.my.hendisantika.parallelcalls.mock.dto.MockResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-template-parallel-calls
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/10/24
 * Time: 17.25
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/mock")
public class MockController {

    private static final Logger logger = LoggerFactory.getLogger(MockController.class);

    // Simulates mock response with delay
    @GetMapping("/data/{id}")
    public MockResponse getMockData(@PathVariable String id) throws InterruptedException {
        logger.info("Received a request for mock data with id: {}", id);

        Thread.sleep(3000); // Adding a delay to simulate real-world scenarios

        MockResponse response = new MockResponse();
        response.setId(id);
        response.setMessage("Mock data for ID " + id);
        logger.info("Response sent for mock data with id: {}", id);
        return response;
    }

    // Simulates exception response
    @GetMapping("/data/{id}/exception")
    public MockResponse getMockDataException(@PathVariable String id) {
        logger.info("Received a request for mock data with id: {}", id);

        throw new RuntimeException("Something went wrong");
    }
}
