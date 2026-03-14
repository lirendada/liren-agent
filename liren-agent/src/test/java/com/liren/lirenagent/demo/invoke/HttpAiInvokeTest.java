package com.liren.lirenagent.demo.invoke;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HttpAiInvokeTest {
    @Autowired
    private HttpAiInvoke httpAiInvoke;

    @Test
    void run() {
        httpAiInvoke.run();
    }
}