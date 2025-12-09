package org.urhaug.repositoryscorer.scorer;


import org.springframework.stereotype.Service;
import org.urhaug.repositoryscorer.github.GithubClient;
import org.urhaug.repositoryscorer.scorer.algorithms.ScoringAlgorithm;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScoreService {

    private final GithubClient githubClient;
    private final ScoringAlgorithm scorer;

    public ScoreService(GithubClient githubClient, ScoringAlgorithm scorer) {
        this.githubClient = githubClient;
        this.scorer = scorer;
    }

    public List<RepositoryScore> getScoredRepositories(String language, LocalDate earliestCreatedDate) {
        var repositoryDetails = githubClient.getRepositoriesDetails(language, earliestCreatedDate);

        return repositoryDetails
                .items()
                .stream()
                .map(repositoryDetail -> new RepositoryScore(
                        repositoryDetail.name(),
                        language,
                        repositoryDetail.createdAt(),
                        scorer.score(repositoryDetail)

                ))
                .toList();
    }
}

