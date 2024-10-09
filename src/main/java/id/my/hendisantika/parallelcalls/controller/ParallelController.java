package id.my.hendisantika.parallelcalls.controller;

import id.my.hendisantika.parallelcalls.service.ParallelService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-template-parallel-calls
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/10/24
 * Time: 17.29
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParallelController {

    private static final Logger logger = LoggerFactory.getLogger(ParallelController.class);

    private final ParallelService parallelService;
}