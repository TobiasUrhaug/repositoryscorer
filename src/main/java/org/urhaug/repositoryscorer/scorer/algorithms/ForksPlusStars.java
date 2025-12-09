package org.urhaug.repositoryscorer.scorer.algorithms;

import org.springframework.stereotype.Component;
import org.urhaug.repositoryscorer.github.GithubRepositoryDetails;

@Component
public class ForksPlusStars implements ScoringAlgorithm {
    @Override
    public double score(GithubRepositoryDetails repoDetails) {
        return repoDetails.forks() + repoDetails.stars();
    }
}
