package com.liren.lirenagent.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class HttpAiInvoke implements CommandLineRunner {

    @Value("${dashscope.api-key}")
    private String apiKey;

    @Override
    public void run(String args[]) {
        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";

        String jsonBody = """
        {
            "model": "qwen-plus",
            "input":{
                "messages":[
                    {
                        "role": "system",
                        "content": "You are a helpful assistant."
                    },
                    {
                        "role": "user",
                        "content": "你是谁？"
                    }
                ]
            },
            "parameters": {
                "result_format": "message"
            }
        }
        """;

        HttpResponse response = HttpRequest.post(url)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .timeout(20000)
                .execute();

        String result = response.body();
        System.out.println(result);
    }
}