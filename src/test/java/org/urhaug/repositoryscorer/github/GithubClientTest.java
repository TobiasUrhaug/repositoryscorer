package org.urhaug.repositoryscorer.github;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.restclient.test.MockServerRestClientCustomizer;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(GithubClient.class)
@EnableConfigurationProperties(GithubProperties.class)
class GithubClientTest {

    @Autowired
    private GithubClient githubClient;

    @Autowired
    private MockServerRestClientCustomizer serverCustomizer;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("github.perPage", () -> 35);
        registry.add("github.apiUrl", () -> "https://mocked.github.com");
    }

    @Test
    void whenPayloadIsValid_aResponseIsExtracted() {
        var validJson = read("/payloads/github-repository-response.json");

        serverCustomizer
                .getServer()
                .expect(requestTo(
                        "https://mocked.github.com/search/repositories?q=language:java%20created:%3E%3D2024-01-01&per_page=35"
                ))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(validJson, MediaType.APPLICATION_JSON));

        GithubRepositoryResponse response = githubClient.getRepositoriesDetails("java", LocalDate.parse("2024-01-01"));

        var firstRepo = response.items().getFirst();

        assertThat(response.items()).hasSize(2);
        assertThat(firstRepo.name()).isEqualTo("TetrisLite");
        assertThat(firstRepo.createdAt()).isEqualTo("2025-10-06");
        assertThat(firstRepo.stars()).isEqualTo(18);
        assertThat(firstRepo.forks()).isEqualTo(0);
    }

    static String read(String path) {
        try {
            return new String(
                    Objects.requireNonNull(GithubClientTest.class.getResourceAsStream(path)).readAllBytes(),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

