package org.urhaug.repositoryscorer.scorer.algorithms;

import org.springframework.stereotype.Component;
import org.urhaug.repositoryscorer.scorer.RepositoryScoringFactors;

@Component
public class ForksPlusStars implements ScoringAlgorithm {
    @Override
    public double score(RepositoryScoringFactors repoDetails) {
        return repoDetails.forks() + repoDetails.stars();
    }
}
