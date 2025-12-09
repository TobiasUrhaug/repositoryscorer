package org.urhaug.repositoryscorer.scorer;

import org.urhaug.repositoryscorer.githubclient.GithubRepositoryDetails;

public interface ScoringAlgorithm {
    double score(GithubRepositoryDetails repoDetails);
}
