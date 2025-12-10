package org.urhaug.repositoryscorer.github;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Component
public class GithubClient {

    private final RestClient restClient;
    private final GithubProperties properties;

    public GithubClient(RestClient.Builder builder, GithubProperties properties) {
        this.restClient = builder
                .baseUrl(properties.apiUrl())
                .build();
        this.properties = properties;
    }

    public GithubRepositoryResponse getRepositoriesDetails(String language, LocalDate earliestCreatedDate) {
        String query = "language:" + language + " created:>=" + earliestCreatedDate;

        return restClient.get()
               .uri(uriBuilder -> uriBuilder
                       .path("/search/repositories")
                       .queryParam("q", query)
                       .queryParam("per_page", properties.perPage())
                       .build())
               .retrieve()
               .body(GithubRepositoryResponse.class);
    }
}
