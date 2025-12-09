package org.urhaug.repositoryscorer.scorer.algorithms;

import org.urhaug.repositoryscorer.scorer.RepositoryScoringFactors;

public interface ScoringAlgorithm {
    double score(RepositoryScoringFactors repoDetails);
}
