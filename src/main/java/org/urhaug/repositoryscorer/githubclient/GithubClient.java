package org.urhaug.repositoryscorer.githubclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Component
public class GithubClient {

    private final RestClient restClient;

    public GithubClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://api.github.com")
                .build();
    }

    public GithubRepositoryResponse getRepositoriesDetails(String language, LocalDate earliestCreatedDate) {
        String query = "language:" + language + " created:>=" + earliestCreatedDate;

        return restClient.get()
               .uri(uriBuilder -> uriBuilder
                       .path("/search/repositories")
                       .queryParam("q", query)
                       .build())
               .retrieve()
               .body(GithubRepositoryResponse.class);
    }
}
