package org.urhaug.repositoryscorer.github;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record GithubRepositoryDetails(
        String name,
        @JsonProperty("created_at")
        LocalDate createdAt,
        @JsonProperty("updated_at")
        LocalDate updatedAt,
        @JsonProperty("forks_count")
        int forks,
        @JsonProperty("stargazers_count")
        int stars
)
{ }
