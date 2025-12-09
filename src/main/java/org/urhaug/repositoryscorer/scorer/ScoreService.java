package org.urhaug.repositoryscorer.scorer;


import org.springframework.stereotype.Service;
import org.urhaug.repositoryscorer.githubclient.GithubClient;
import org.urhaug.repositoryscorer.scorer.dto.RepositoryScore;
import org.urhaug.repositoryscorer.scorer.dto.ScoreResponse;

import java.time.LocalDate;

@Service
public class ScoreService {

    private final GithubClient githubClient;

    public ScoreService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public ScoreResponse getScoredRepositories(String language, LocalDate earliestCreatedDate) {
        var repositoryDetails = githubClient.getRepositoriesDetails(language, earliestCreatedDate);
        var hardCodedScore = 2.3;

        var scores = repositoryDetails
                .items()
                .stream()
                .map(repositoryDetail -> new RepositoryScore(
                        repositoryDetail.name(),
                        language,
                        repositoryDetail.createdAt(),
                        hardCodedScore

                ))
                .toList();

        return new ScoreResponse(scores);
    }
}

