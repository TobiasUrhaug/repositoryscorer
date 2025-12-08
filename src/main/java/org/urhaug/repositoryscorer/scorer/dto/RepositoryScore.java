package org.urhaug.repositoryscorer.scorer.dto;

import java.time.LocalDate;

public record RepositoryScore(
        String name,
        String language,
        LocalDate createdDate,
        double score
) {}
