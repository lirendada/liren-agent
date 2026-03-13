package com.liren.lirenagent.demo.invoke;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SdkAiInvoke implements CommandLineRunner {

    @Value("${dashscope.api-key}")
    private String apiKey;

    @Override
    public void run(String... args) {
        try {
            GenerationResult result = callWithMessage();
            System.out.println(JsonUtils.toJson(result));
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            System.err.println("An error occurred while calling the generation service: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public GenerationResult callWithMessage() throws ApiException, NoApiKeyException, InputRequiredException {
        Generation gen = new Generation();

        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("You are a helpful assistant.")
                .build();

        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content("你是谁？")
                .build();

        GenerationParam param = GenerationParam.builder()
                .apiKey(apiKey)
                .model("qwen-plus")
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();

        return gen.call(param);
    }
}