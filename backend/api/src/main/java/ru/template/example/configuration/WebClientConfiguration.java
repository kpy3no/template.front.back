package ru.template.example.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(WebClientConfiguration.Settings.class)
public class WebClientConfiguration {

    private final Settings settings;

    @Bean
    public WebClient webClient(ObjectMapper mapper) {
        var tcpClient = TcpClient.create()
                .option(CONNECT_TIMEOUT_MILLIS, settings.connectTimeout)
                .doOnConnected(connection ->
                        connection.addHandlerLast(new ReadTimeoutHandler(settings.readTimeout, MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(settings.writeTimeout, MILLISECONDS)));

        var strategies = ExchangeStrategies
                .builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(
                            new Jackson2JsonEncoder(mapper, APPLICATION_JSON));
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(
                            new Jackson2JsonDecoder(mapper, APPLICATION_JSON));

                }).build();

        return WebClient.builder()
                .baseUrl(settings.baseUrl)
                .exchangeStrategies(strategies)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }

    @Setter
    @NoArgsConstructor
    @ConfigurationProperties(prefix = "egress")
    static final class Settings {
        private String baseUrl;
        private int connectTimeout;
        private int readTimeout;
        private int writeTimeout;
    }
}
