package org.urhaug.repositoryscorer.github;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "github")
public record GithubProperties(
        int perPage,
        String apiUrl
) {}
