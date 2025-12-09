package org.urhaug.repositoryscorer.githubclient;

import java.util.List;

public record GithubRepositoryResponse(List<GithubRepositoryDetails> items)
{ }
