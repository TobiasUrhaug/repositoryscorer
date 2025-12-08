package org.urhaug.repositoryscorer.githubclient;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Component
public class GithubClient {

    public List<GithubRepositoryDetails> getRepositoriesDetails(String language, LocalDate earliestCreatedDate) {
        return Collections.emptyList();
    }
}
