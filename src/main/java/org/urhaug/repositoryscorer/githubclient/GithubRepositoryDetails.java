package org.urhaug.repositoryscorer.githubclient;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record GithubRepositoryDetails(
        String name,
        @JsonProperty("created_at")
        LocalDate createdAt
)
{ }
