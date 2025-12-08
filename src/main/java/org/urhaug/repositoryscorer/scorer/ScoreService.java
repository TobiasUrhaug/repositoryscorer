package org.urhaug.repositoryscorer.scorer;


import org.springframework.stereotype.Service;
import org.urhaug.repositoryscorer.scorer.dto.RepositoryScore;
import org.urhaug.repositoryscorer.scorer.dto.ScoreResponse;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScoreService {

    public ScoreResponse getScoredRepositories(String language, LocalDate createdDate) {
        List<RepositoryScore> scores = List.of(
                new RepositoryScore(
                        "repo-one",
                        "Java",
                        LocalDate.of(2023, 1, 15),
                        87.5
                ),
                new RepositoryScore(
                        "repo-two",
                        "Kotlin",
                        LocalDate.of(2025, 3, 23),
                        92.0
                )
        );

        return new ScoreResponse(scores);
    }
}

