package org.urhaug.repositoryscorer.github;

import org.urhaug.repositoryscorer.scorer.RepositoryScoringFactors;

import java.util.List;

public record GithubRepositoryResponse(List<RepositoryScoringFactors> items)
{ }
