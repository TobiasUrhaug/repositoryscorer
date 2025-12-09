package org.urhaug.repositoryscorer.scorer;


import org.springframework.stereotype.Service;
import org.urhaug.repositoryscorer.githubclient.GithubClient;
import org.urhaug.repositoryscorer.scorer.dto.RepositoryScore;
import org.urhaug.repositoryscorer.scorer.dto.ScoreResponse;

import java.time.LocalDate;

@Service
public class ScoreService {

    private final GithubClient githubClient;
    private final ScoringAlgorithm scorer;

    public ScoreService(GithubClient githubClient, ScoringAlgorithm scorer) {
        this.githubClient = githubClient;
        this.scorer = scorer;
    }

    public ScoreResponse getScoredRepositories(String language, LocalDate earliestCreatedDate) {
        var repositoryDetails = githubClient.getRepositoriesDetails(language, earliestCreatedDate);

        var scores = repositoryDetails
                .items()
                .stream()
                .map(repositoryDetail -> new RepositoryScore(
                        repositoryDetail.name(),
                        language,
                        repositoryDetail.createdAt(),
                        scorer.score(repositoryDetail)

                ))
                .toList();

        return new ScoreResponse(scores);
    }
}

