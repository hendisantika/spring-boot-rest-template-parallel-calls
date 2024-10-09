package id.my.hendisantika.parallelcalls.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
@Service
@RequiredArgsConstructor
public class ParallelService {

    private final RestTemplate restTemplate;
}
