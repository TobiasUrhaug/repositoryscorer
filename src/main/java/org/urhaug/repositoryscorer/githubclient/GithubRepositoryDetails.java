package org.urhaug.repositoryscorer.githubclient;

import java.time.LocalDate;

public record GithubRepositoryDetails(
        String name,
        LocalDate createdDate
)
{ }
