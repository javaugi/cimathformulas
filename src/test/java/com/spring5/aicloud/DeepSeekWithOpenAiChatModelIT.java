/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aicloud;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionRequest.AudioParameters;
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionRequest.AudioParameters.AudioResponseFormat;
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionRequest.AudioParameters.Voice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Disabled;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author javaugi
 */
@SpringBootTest
@EnabledIfEnvironmentVariable(named = "DEEPSEEK_API_KEY", matches = ".+")
@Disabled("Requires DeepSeek credits")
public class DeepSeekWithOpenAiChatModelIT {

    private static final Logger logger = LoggerFactory.getLogger(DeepSeekWithOpenAiChatModelIT.class);
    
    @Value("classpath:/prompts/system-message.st")
    private Resource systemResource;

    @Autowired
    private @Qualifier("deekseekOpenAiChatModel") OpenAiChatModel chatModel;

    @Test
    public void roleTest() {
        UserMessage userMessage = new UserMessage(
                "Tell me about 3 famous pirates from the Golden Age of Piracy and what they did.");
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(this.systemResource);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", "Bob", "voice", "pirate"));
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
        ChatResponse response = this.chatModel.call(prompt);
        assertThat(response.getResults()).hasSize(1);
        assertThat(response.getResults().get(0).getOutput().getText()).contains("Blackbeard");
    }

    @Test
    public void testMessageHistory() {
        UserMessage userMessage = new UserMessage(
                "Tell me about 3 famous pirates from the Golden Age of Piracy and why they did.");
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(this.systemResource);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", "Bob", "voice", "pirate"));
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        ChatResponse response = this.chatModel.call(prompt);
        assertThat(response.getResult().getOutput().getText()).containsAnyOf("Blackbeard", "Bartholomew");

        var promptWithMessageHistory = new Prompt(List.of(new UserMessage("Dummy"), response.getResult().getOutput(),
                new UserMessage("Repeat the last assistant message.")));
        response = this.chatModel.call(promptWithMessageHistory);

        assertThat(response.getResult().getOutput().getText()).containsAnyOf("Blackbeard", "Bartholomew");
    }

    /*
    @Test
    public void streamCompletenessTest() throws InterruptedException {
        UserMessage userMessage = new UserMessage(
                "List ALL natural numbers in range [1, 1000]. Make sure to not omit any.");
        Prompt prompt = new Prompt(List.of(userMessage));

        StringBuilder answer = new StringBuilder();
        CountDownLatch latch = new CountDownLatch(1);

        Flux<ChatResponse> chatResponseFlux = this.streamingChatModel.stream(prompt).doOnNext(chatResponse -> {
            String responseContent = chatResponse.getResults().get(0).getOutput().getText();
            answer.append(responseContent);
        }).doOnComplete(() -> {
            logger.info(answer.toString());
            latch.countDown();
        });
        chatResponseFlux.subscribe();
        assertThat(latch.await(120, TimeUnit.SECONDS)).isTrue();
        IntStream.rangeClosed(1, 1000).forEach(n -> assertThat(answer).contains(String.valueOf(n)));
    } 
    // */

    /*
    @Test
    public void streamCompletenessTestWithChatResponse() throws InterruptedException {
        UserMessage userMessage = new UserMessage("Who is George Washington? - use first as 1st");
        Prompt prompt = new Prompt(List.of(userMessage));

        StringBuilder answer = new StringBuilder();
        CountDownLatch latch = new CountDownLatch(1);

        ChatClient chatClient = ChatClient.builder(this.openAiChatModel).build();

        Flux<ChatResponse> chatResponseFlux = chatClient.prompt(prompt)
                .stream()
                .chatResponse()
                .doOnNext(chatResponse -> {
                    String responseContent = chatResponse.getResults().get(0).getOutput().getText();
                    answer.append(responseContent);
                })
                .doOnComplete(() -> {
                    logger.info(answer.toString());
                    latch.countDown();
                });
        chatResponseFlux.subscribe();
        assertThat(latch.await(120, TimeUnit.SECONDS)).isTrue();
        assertThat(answer).contains("1st ");
    } 
    // */

    /*
    @Test
    public void ensureChatResponseAsContentDoesNotSwallowBlankSpace() throws InterruptedException {
        UserMessage userMessage = new UserMessage("Who is George Washington? - use first as 1st");
        Prompt prompt = new Prompt(List.of(userMessage));

        StringBuilder answer = new StringBuilder();
        CountDownLatch latch = new CountDownLatch(1);

        ChatClient chatClient = ChatClient.builder(this.openAiChatModel).build();

        Flux<String> chatResponseFlux = chatClient.prompt(prompt)
                .stream()
                .content()
                .doOnNext(answer::append)
                .doOnComplete(() -> {
                    logger.info(answer.toString());
                    latch.countDown();
                });
        chatResponseFlux.subscribe();
        assertThat(latch.await(120, TimeUnit.SECONDS)).isTrue();
        assertThat(answer).contains("1st ");
    } 
    // */

    /*
    @Test
    public void streamRoleTest() {
        UserMessage userMessage = new UserMessage(
                "Tell me about 3 famous pirates from the Golden Age of Piracy and what they did.");
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(this.systemResource);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", "Bob", "voice", "pirate"));
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
        Flux<ChatResponse> flux = this.streamingChatModel.stream(prompt);

        List<ChatResponse> responses = flux.collectList().block();
        assertThat(responses.size()).isGreaterThan(1);

        String stitchedResponseContent = responses.stream()
                .map(ChatResponse::getResults)
                .flatMap(List::stream)
                .map(Generation::getOutput)
                .map(AssistantMessage::getText)
                .collect(Collectors.joining());

        assertThat(stitchedResponseContent).contains("Blackbeard");

    }
    // */

    @Test
    public void streamingWithTokenUsage() {
        var promptOptions = OpenAiChatOptions.builder().streamUsage(true).seed(1).build();

        var prompt = new Prompt("List two colors of the Polish flag. Be brief.", promptOptions);
        var streamingTokenUsage = this.chatModel.stream(prompt).blockLast().getMetadata().getUsage();
        var referenceTokenUsage = this.chatModel.call(prompt).getMetadata().getUsage();

        assertThat(streamingTokenUsage.getPromptTokens()).isGreaterThan(0);
        assertThat(streamingTokenUsage.getCompletionTokens()).isGreaterThan(0);
        assertThat(streamingTokenUsage.getTotalTokens()).isGreaterThan(0);

        assertThat(streamingTokenUsage.getPromptTokens()).isCloseTo(referenceTokenUsage.getPromptTokens(),
                Percentage.withPercentage(25));
        assertThat(streamingTokenUsage.getCompletionTokens()).isCloseTo(referenceTokenUsage.getCompletionTokens(),
                Percentage.withPercentage(25));
        assertThat(streamingTokenUsage.getTotalTokens()).isCloseTo(referenceTokenUsage.getTotalTokens(),
                Percentage.withPercentage(25));

    }

    @Test
    public void listOutputConverter() {
        DefaultConversionService conversionService = new DefaultConversionService();
        ListOutputConverter outputConverter = new ListOutputConverter(conversionService);

        String format = outputConverter.getFormat();
        String template = """
				List five {subject}
				{format}
				""";
        PromptTemplate promptTemplate = new PromptTemplate(template,
                Map.of("subject", "ice cream flavors", "format", format));
        Prompt prompt = new Prompt(promptTemplate.createMessage());
        Generation generation = this.chatModel.call(prompt).getResult();

        List<String> list = outputConverter.convert(generation.getOutput().getText());
        assertThat(list).hasSize(5);

    }

    @Test
    public void mapOutputConverter() {
        MapOutputConverter outputConverter = new MapOutputConverter();

        String format = outputConverter.getFormat();
        String template = """
				Provide me a List of {subject}
				{format}
				""";
        PromptTemplate promptTemplate = new PromptTemplate(template,
                Map.of("subject", "numbers from 1 to 9 under they key name 'numbers'", "format", format));
        Prompt prompt = new Prompt(promptTemplate.createMessage());
        Generation generation = this.chatModel.call(prompt).getResult();

        Map<String, Object> result = outputConverter.convert(generation.getOutput().getText());
        assertThat(result.get("numbers")).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    }

    /*
    @Test
    public void beanOutputConverter() {

        BeanOutputConverter<ActorsFilms> outputConverter = new BeanOutputConverter<>(ActorsFilms.class);

        String format = outputConverter.getFormat();
        String template = """
				Generate the filmography for a random actor.
				{format}
				""";
        PromptTemplate promptTemplate = new PromptTemplate(template, Map.of("format", format));
        Prompt prompt = new Prompt(promptTemplate.createMessage());
        Generation generation = this.chatModel.call(prompt).getResult();

        ActorsFilms actorsFilms = outputConverter.convert(generation.getOutput().getText());
    }
    // */

    /*
    @Test
    public void beanOutputConverterRecords() {

        BeanOutputConverter<ActorsFilmsRecord> outputConverter = new BeanOutputConverter<>(ActorsFilmsRecord.class);

        String format = outputConverter.getFormat();
        String template = """
				Generate the filmography of 5 movies for Tom Hanks.
				{format}
				""";
        PromptTemplate promptTemplate = new PromptTemplate(template, Map.of("format", format));
        Prompt prompt = new Prompt(promptTemplate.createMessage());
        Generation generation = this.chatModel.call(prompt).getResult();

        ActorsFilmsRecord actorsFilms = outputConverter.convert(generation.getOutput().getText());
        logger.info("" + actorsFilms);
        assertThat(actorsFilms.actor()).isEqualTo("Tom Hanks");
        assertThat(actorsFilms.movies()).hasSize(5);
    }
    // */
    /*

    public @Test
    void beanStreamOutputConverterRecords() {

        BeanOutputConverter<ActorsFilmsRecord> outputConverter = new BeanOutputConverter<>(ActorsFilmsRecord.class);

        String format = outputConverter.getFormat();
        String template = """
				Generate the filmography of 5 movies for Tom Hanks.
				{format}
				""";
        PromptTemplate promptTemplate = new PromptTemplate(template, Map.of("format", format));
        Prompt prompt = new Prompt(promptTemplate.createMessage());

        String generationTextFromStream = this.streamingChatModel.stream(prompt)
                .collectList()
                .block()
                .stream()
                .map(ChatResponse::getResults)
                .flatMap(List::stream)
                .map(Generation::getOutput)
                .map(AssistantMessage::getText)
                .collect(Collectors.joining());

        ActorsFilmsRecord actorsFilms = outputConverter.convert(generationTextFromStream);
        logger.info("" + actorsFilms);
        assertThat(actorsFilms.actor()).isEqualTo("Tom Hanks");
        assertThat(actorsFilms.movies()).hasSize(5);
    } 
    // */

    /*
    @Test
    @Deprecated
    public void functionCallTestDeprecated() {

        UserMessage userMessage = new UserMessage("What's the weather like in San Francisco, Tokyo, and Paris?");

        List<Message> messages = new ArrayList<>(List.of(userMessage));

        var promptOptions = OpenAiChatOptions.builder()
                .model(OpenAiApi.ChatModel.GPT_4_O.getValue())
                .toolCallbacks(List.of(FunctionToolCallback.builder("getCurrentWeather", new MockWeatherService())
                        .description("Get the weather in location")
                        .inputType(MockWeatherService.Request.class)
                        .build()))
                .build();

        ChatResponse response = this.chatModel.call(new Prompt(messages, promptOptions));

        logger.info("Response: {}", response);

        assertThat(response.getResult().getOutput().getText()).containsAnyOf("30.0", "30");
        assertThat(response.getResult().getOutput().getText()).containsAnyOf("10.0", "10");
        assertThat(response.getResult().getOutput().getText()).containsAnyOf("15.0", "15");
    } 
    // */

    /*
    @Test
    public void functionCallTest() {

        UserMessage userMessage = new UserMessage("What's the weather like in San Francisco, Tokyo, and Paris?");

        List<Message> messages = new ArrayList<>(List.of(userMessage));

        var promptOptions = OpenAiChatOptions.builder()
                .model(OpenAiApi.ChatModel.GPT_4_O.getValue())
                .toolCallbacks(List.of(FunctionToolCallback.builder("getCurrentWeather", new MockWeatherService())
                        .description("Get the weather in location")
                        .inputType(MockWeatherService.Request.class)
                        .build()))
                .build();

        ChatResponse response = this.chatModel.call(new Prompt(messages, promptOptions));

        logger.info("Response: {}", response);

        assertThat(response.getResult().getOutput().getText()).contains("30", "10", "15");
    } 
    // */

    /*
    @Test
    public void streamFunctionCallTest() {

        UserMessage userMessage = new UserMessage("What's the weather like in San Francisco, Tokyo, and Paris?");

        List<Message> messages = new ArrayList<>(List.of(userMessage));

        var promptOptions = OpenAiChatOptions.builder()
                // .withModel(OpenAiApi.ChatModel.GPT_4_TURBO_PREVIEW.getValue())
                .toolCallbacks(List.of(FunctionToolCallback.builder("getCurrentWeather", new MockWeatherService())
                        .description("Get the weather in location")
                        .inputType(MockWeatherService.Request.class)
                        .build()))
                .build();

        Flux<ChatResponse> response = this.streamingChatModel.stream(new Prompt(messages, promptOptions));

        String content = response.collectList()
                .block()
                .stream()
                .map(ChatResponse::getResults)
                .flatMap(List::stream)
                .map(Generation::getOutput)
                .map(AssistantMessage::getText)
                .collect(Collectors.joining());
        logger.info("Response: {}", content);

        assertThat(content).containsAnyOf("30.0", "30");
        assertThat(content).containsAnyOf("10.0", "10");
        assertThat(content).containsAnyOf("15.0", "15");
    } 
    // */

    /*
    @Test
    public void functionCallUsageTest() {

        UserMessage userMessage = new UserMessage("What's the weather like in San Francisco, Tokyo, and Paris?");

        List<Message> messages = new ArrayList<>(List.of(userMessage));

        var promptOptions = OpenAiChatOptions.builder()
                // .withModel(OpenAiApi.ChatModel.GPT_4_TURBO_PREVIEW.getValue())
                .toolCallbacks(List.of(FunctionToolCallback.builder("getCurrentWeather", new MockWeatherService())
                        .description("Get the weather in location")
                        .inputType(MockWeatherService.Request.class)
                        .build()))
                .build();

        ChatResponse chatResponse = this.chatModel.call(new Prompt(messages, promptOptions));
        logger.info("Response: {}", chatResponse);
        Usage usage = chatResponse.getMetadata().getUsage();

        logger.info("Usage: {}", usage);
        assertThat(usage).isNotNull();
        assertThat(usage).isNotInstanceOf(EmptyUsage.class);
        assertThat(usage).isInstanceOf(DefaultUsage.class);
        assertThat(usage.getPromptTokens()).isGreaterThan(450).isLessThan(600);
        assertThat(usage.getCompletionTokens()).isGreaterThan(230).isLessThan(360);
        assertThat(usage.getTotalTokens()).isGreaterThan(680).isLessThan(900);
    } 
    // */

    /*
    public @Test
    void streamFunctionCallUsageTest() {

        UserMessage userMessage = new UserMessage("What's the weather like in San Francisco, Tokyo, and Paris?");

        List<Message> messages = new ArrayList<>(List.of(userMessage));

        var promptOptions = OpenAiChatOptions.builder()
                // .withModel(OpenAiApi.ChatModel.GPT_4_TURBO_PREVIEW.getValue())
                .toolCallbacks(List.of(FunctionToolCallback.builder("getCurrentWeather", new MockWeatherService())
                        .description("Get the weather in location")
                        .inputType(MockWeatherService.Request.class)
                        .build()))
                .streamUsage(true)
                .build();

        Flux<ChatResponse> response = this.streamingChatModel.stream(new Prompt(messages, promptOptions));
        Usage usage = response.last().block().getMetadata().getUsage();

        logger.info("Usage: {}", usage);
        assertThat(usage).isNotNull();
        assertThat(usage).isNotInstanceOf(EmptyUsage.class);
        assertThat(usage).isInstanceOf(DefaultUsage.class);
        assertThat(usage.getPromptTokens()).isGreaterThan(450).isLessThan(600);
        assertThat(usage.getCompletionTokens()).isGreaterThan(230).isLessThan(360);
        assertThat(usage.getTotalTokens()).isGreaterThan(680).isLessThan(960);
    }
    // */

    /*
    @ParameterizedTest(name = "{0} : {displayName} ")
    @ValueSource(strings = {"gpt-4o"})
    public void multiModalityEmbeddedImage(String modelName) throws IOException {

        var imageData = new ClassPathResource("/test.png");

        var userMessage = new UserMessage("Explain what do you see on this picture?",
                List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageData)));

        var response = this.chatModel
                .call(new Prompt(List.of(userMessage), OpenAiChatOptions.builder().model(modelName).build()));

        logger.info(response.getResult().getOutput().getText());
        assertThat(response.getResult().getOutput().getText()).containsAnyOf("bananas", "apple", "bowl", "basket",
                "fruit stand");
    } 
    // */

    /*
    @ParameterizedTest(name = "{0} : {displayName} ")
    @ValueSource(strings = {"gpt-4o"})
    public void multiModalityImageUrl(String modelName) throws IOException {

        var userMessage = new UserMessage("Explain what do you see on this picture?",
                List.of(Media.builder()
                        .mimeType(MimeTypeUtils.IMAGE_PNG)
                        .data(new URL("https://docs.spring.io/spring-ai/reference/_images/multimodal.test.png"))
                        .build()));

        ChatResponse response = this.chatModel
                .call(new Prompt(List.of(userMessage), OpenAiChatOptions.builder().model(modelName).build()));

        logger.info(response.getResult().getOutput().getText());
        assertThat(response.getResult().getOutput().getText()).containsAnyOf("bananas", "apple", "bowl", "basket",
                "fruit stand");
    } 
    // */

    /*
    @Test
    public void streamingMultiModalityImageUrl() throws IOException {

        var userMessage = new UserMessage("Explain what do you see on this picture?",
                List.of(Media.builder()
                        .mimeType(MimeTypeUtils.IMAGE_PNG)
                        .data(new URL("https://docs.spring.io/spring-ai/reference/_images/multimodal.test.png"))
                        .build()));

        Flux<ChatResponse> response = this.streamingChatModel.stream(new Prompt(List.of(userMessage),
                OpenAiChatOptions.builder().model(OpenAiApi.ChatModel.GPT_4_O.getValue()).build()));

        String content = response.collectList()
                .block()
                .stream()
                .map(ChatResponse::getResults)
                .flatMap(List::stream)
                .map(Generation::getOutput)
                .map(AssistantMessage::getText)
                .collect(Collectors.joining());
        logger.info("Response: {}", content);
        assertThat(content).containsAnyOf("bananas", "apple", "bowl", "basket", "fruit stand");
    }
    // */

    @ParameterizedTest(name = "{0} : {displayName} ")
    @ValueSource(strings = {"gpt-4o-audio-preview"})
    public void multiModalityOutputAudio(String modelName) throws IOException {
        var userMessage = new UserMessage("Tell me joke about Spring Framework");

        ChatResponse response = this.chatModel.call(new Prompt(List.of(userMessage),
                OpenAiChatOptions.builder()
                        .model(modelName)
                        .outputModalities(List.of("text", "audio"))
                        .outputAudio(new AudioParameters(Voice.ALLOY, AudioResponseFormat.WAV))
                        .build()));

        logger.info(response.getResult().getOutput().getText());
        assertThat(response.getResult().getOutput().getText()).isNotEmpty();

        byte[] audio = response.getResult().getOutput().getMedia().get(0).getDataAsByteArray();
        assertThat(audio).isNotEmpty();
        // AudioPlayer.play(audio);
    }

    @ParameterizedTest(name = "{0} : {displayName} ")
    @ValueSource(strings = {"gpt-4o-audio-preview"})
    public void streamingMultiModalityOutputAudio(String modelName) throws IOException {
        // var audioResource = new ClassPathResource("speech1.mp3");
        var userMessage = new UserMessage("Tell me joke about Spring Framework");

        assertThatThrownBy(() -> this.chatModel
                .stream(new Prompt(List.of(userMessage),
                        OpenAiChatOptions.builder()
                                .model(modelName)
                                .outputModalities(List.of("text", "audio"))
                                .outputAudio(new AudioParameters(Voice.ALLOY, AudioResponseFormat.WAV))
                                .build()))
                .collectList()
                .block()).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Audio parameters are not supported for streaming requests.");
    }
    
    /*
    @ParameterizedTest(name = "{0} : {displayName} ")
    @ValueSource(strings = {"gpt-4o-audio-preview"})
    public void multiModalityInputAudio(String modelName) {
        var audioResource = new ClassPathResource("speech1.mp3");
        var userMessage = new UserMessage("What is this recording about?",
                List.of(new Media(MimeTypeUtils.parseMimeType("audio/mp3"), audioResource)));

        ChatResponse response = this.chatModel
                .call(new Prompt(List.of(userMessage), ChatOptions.builder().model(modelName).build()));

        logger.info(response.getResult().getOutput().getText());
        assertThat(response.getResult().getOutput().getText()).containsIgnoringCase("hobbits");
        assertThat(response.getMetadata().getModel()).containsIgnoringCase(modelName);
    }
    // */

    /*
    @ParameterizedTest(name = "{0} : {displayName} ")
    @ValueSource(strings = {"gpt-4o-audio-preview"})
    public void streamingMultiModalityInputAudio(String modelName) {
        var audioResource = new ClassPathResource("speech1.mp3");
        var userMessage = new UserMessage("What is this recording about?",
                List.of(new Media(MimeTypeUtils.parseMimeType("audio/mp3"), audioResource)));

        Flux<ChatResponse> response = this.chatModel
                .stream(new Prompt(List.of(userMessage), OpenAiChatOptions.builder().model(modelName).build()));

        String content = response.collectList()
                .block()
                .stream()
                .map(ChatResponse::getResults)
                .flatMap(List::stream)
                .map(Generation::getOutput)
                .map(AssistantMessage::getText)
                .collect(Collectors.joining());
        logger.info("Response: {}", content);
        assertThat(content).containsIgnoringCase("hobbits");
    }
    // */

    @Test
    public void validateCallResponseMetadata() {
        String model = OpenAiApi.ChatModel.GPT_3_5_TURBO.getName();
        // @formatter:off
        ChatResponse response = ChatClient.create(this.chatModel).prompt()
                .options(OpenAiChatOptions.builder().model(model).build())
                .user("Tell me about 3 famous pirates from the Golden Age of Piracy and what they did")
                .call()
                .chatResponse();
        // @formatter:on

        logger.info(response.toString());
        assertThat(response.getMetadata().getId()).isNotEmpty();
        assertThat(response.getMetadata().getModel()).containsIgnoringCase(model);
        assertThat(response.getMetadata().getUsage().getPromptTokens()).isPositive();
        assertThat(response.getMetadata().getUsage().getCompletionTokens()).isPositive();
        assertThat(response.getMetadata().getUsage().getTotalTokens()).isPositive();
    }

    /*
    @Test
    public void validateStoreAndMetadata() {
        OpenAiChatOptions options = OpenAiChatOptions.builder().store(true).metadata(Map.of("type", "dev")).build();

        ChatResponse response = this.openAiChatModel.call(new Prompt("Tell me a joke", options));

        assertThat(response).isNotNull();
    }
    // */
}
