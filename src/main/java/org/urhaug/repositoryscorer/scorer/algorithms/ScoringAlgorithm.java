package org.urhaug.repositoryscorer.scorer.algorithms;

import org.urhaug.repositoryscorer.github.GithubRepositoryDetails;

public interface ScoringAlgorithm {
    double score(GithubRepositoryDetails repoDetails);
}
