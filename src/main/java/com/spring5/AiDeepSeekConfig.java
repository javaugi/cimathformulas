/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 *
 * @author javaugi
 */
@Configuration
public class AiDeepSeekConfig {

    private static final Logger log = LoggerFactory.getLogger(AiDeepSeekConfig.class);
    private static final String DEEPSEEK_BASE_URL = "https://api.deepseek.com";
    private static final String DEEPSEEK_API_KEY = "sk-258df0f85ebf4afbb9f37ab96d37dfc0";
    private static final String DEFAULT_DEEPSEEK_MODEL = "deepseek-chat";

    @Value("classpath:/prompts/system-message.st")
    private Resource systemResource;

    @Bean
    public OpenAiApi chatCompletionApi() {
        return OpenAiApi.builder().baseUrl(DEEPSEEK_BASE_URL).apiKey(DEEPSEEK_API_KEY).build();
    }

    @Bean
    public OpenAiChatModel openAiClient(OpenAiApi openAiApi) {
        return OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(OpenAiChatOptions.builder().model(DEFAULT_DEEPSEEK_MODEL).build())
                .build();
    }

}
/* test
curl https://api.deepseek.com/chat/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <DeepSeek API Key>" \
  -d '{
        "model": "deepseek-chat",
        "messages": [
          {"role": "system", "content": "You are a helpful assistant."},
          {"role": "user", "content": "Hello!"}
        ],
        "stream": false
      }'
*/
