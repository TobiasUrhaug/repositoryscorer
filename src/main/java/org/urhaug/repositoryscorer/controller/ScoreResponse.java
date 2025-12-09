package org.urhaug.repositoryscorer.controller;

import org.urhaug.repositoryscorer.scorer.RepositoryScore;

import java.util.List;

public record ScoreResponse(List<RepositoryScore> repositories) {}
