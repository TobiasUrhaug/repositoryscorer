package org.urhaug.repositoryscorer.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Component
public class GithubClient {

    @Value("${github.defaultPerPage:30}")
    private int defaultPerPage;
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
                       .queryParam("per_page", defaultPerPage)
                       .build())
               .retrieve()
               .body(GithubRepositoryResponse.class);
    }
}
