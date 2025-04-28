/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 *
 * @author javaugi
 */
@Configuration
public class AiDeepSeekConfig implements CommandLineRunner{

    private static final Logger log = LoggerFactory.getLogger(AiDeepSeekConfig.class);

    @Value("classpath:/prompts/system-message.st")
    private Resource systemResource;
    
    @Value("${spring.ai.deepseek.openai.base-url}")
    protected String dsBaseUrl;
    @Value("${spring.ai.deepseek.openai.api-key}")
    protected String dsApiKey;
    @Value("${spring.ai.deepseek.openai.chat.options.model}")
    protected String dsModelDefault;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("AiDeepSeekConfig with args {} \n url {} api {} \n systemResource {}", Arrays.toString(args), dsBaseUrl, dsApiKey, systemResource); 
    }

    @Bean
    public OpenAiApi chatCompletionApi() {
        return OpenAiApi.builder().baseUrl(dsBaseUrl).apiKey(dsApiKey).build();
    }

    @Bean
    public OpenAiChatModel openAiClient(OpenAiApi openAiApi) {
        return OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(OpenAiChatOptions.builder().model(dsModelDefault).build())
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
