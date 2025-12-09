package org.urhaug.repositoryscorer.scorer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record RepositoryScoringFactors(
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
