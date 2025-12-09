package org.urhaug.repositoryscorer.scorer;

import org.junit.jupiter.api.Test;
import org.urhaug.repositoryscorer.githubclient.GithubRepositoryDetails;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ForksPlusStarsTest {

    @Test
    void scoresARepoAsStarsPlusForks() {
        GithubRepositoryDetails repoDetails =
                new GithubRepositoryDetails(
                        "test",
                        LocalDate.now(),
                        LocalDate.now(),
                        1,
                        3
                );

        ForksPlusStars scorer = new ForksPlusStars();

        assertThat(scorer.score(repoDetails)).isEqualTo(4.0);
    }

}