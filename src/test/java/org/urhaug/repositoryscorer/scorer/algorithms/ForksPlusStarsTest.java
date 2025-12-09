package org.urhaug.repositoryscorer.scorer.algorithms;

import org.junit.jupiter.api.Test;
import org.urhaug.repositoryscorer.scorer.RepositoryScoringFactors;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ForksPlusStarsTest {

    @Test
    void scoresARepoAsStarsPlusForks() {
        RepositoryScoringFactors repoDetails =
                new RepositoryScoringFactors(
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