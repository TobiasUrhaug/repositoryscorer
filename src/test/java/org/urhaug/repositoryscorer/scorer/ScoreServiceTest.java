package org.urhaug.repositoryscorer.scorer;

import org.junit.jupiter.api.Test;
import org.urhaug.repositoryscorer.github.GithubClient;
import org.urhaug.repositoryscorer.github.GithubRepositoryResponse;
import org.urhaug.repositoryscorer.scorer.algorithms.ScoringAlgorithm;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScoreServiceTest {

    @Test
    void returnsScoredRepositories() {
        // Arrange
        GithubClient githubClient = mock(GithubClient.class);
        LocalDate earliestCreatedDate = LocalDate.of(2022, 12, 8);
        List<RepositoryScoringFactors> repositoryDetails = List.of(
                new RepositoryScoringFactors(
                        "test-repo",
                        LocalDate.of(2024, 1, 16),
                        LocalDate.of(2025, 2, 17),
                        1,
                        3
                )
        );
        when(githubClient
                .getRepositoriesDetails("java", earliestCreatedDate))
                .thenReturn(new GithubRepositoryResponse(repositoryDetails));

        ScoringAlgorithm scorer = mock(ScoringAlgorithm.class);
        double mockScore = 5.6;
        when(scorer.score(any())).thenReturn(mockScore);

        ScoreService scoreService = new ScoreService(githubClient, scorer);

        // Act
        List<RepositoryScore> scoredRepositories = scoreService.getScoredRepositories("java", earliestCreatedDate);

        // Assertions
        assertThat(scoredRepositories).isNotNull();
        assertThat(scoredRepositories).hasSize(1);
        assertThat(scoredRepositories)
                .extracting(RepositoryScore::name)
                .containsExactlyInAnyOrder("test-repo");
        assertThat(scoredRepositories)
                .extracting(RepositoryScore::score)
                .containsExactlyInAnyOrder(5.6);
    }
}
