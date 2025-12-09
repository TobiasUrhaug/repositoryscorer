package org.urhaug.repositoryscorer.githubclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.test.MockServerRestClientCustomizer;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

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
class GithubClientTest {

    @Autowired
    private GithubClient githubClient;

    @Autowired
    private MockServerRestClientCustomizer serverCustomizer;

    @Test
    void whenPayloadIsValid_aResponseIsExtracted() {
        var validJson = read("/payloads/github-repository-response.json");

        serverCustomizer
                .getServer()
                .expect(requestTo(
                        "https://api.github.com/search/repositories?q=language:java%20created:%3E%3D2024-01-01"
                ))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(validJson, MediaType.APPLICATION_JSON));

        GithubRepositoryResponse response = githubClient.getRepositoriesDetails("java", LocalDate.parse("2024-01-01"));

        assertThat(response.items()).hasSize(2);
        assertThat(response.items().getFirst().name()).isEqualTo("TetrisLite");
        assertThat(response.items().getFirst().createdAt()).isEqualTo("2025-10-06");
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

