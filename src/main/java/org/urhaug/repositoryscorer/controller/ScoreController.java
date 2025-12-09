package org.urhaug.repositoryscorer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.urhaug.repositoryscorer.scorer.RepositoryScore;
import org.urhaug.repositoryscorer.scorer.ScoreService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/score")
@Tag(name = "Scoring", description = "Calculate repository score")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @Operation(
            summary = "Get repository scores",
            description = """
                Returns a list of scored repositories created on or after a given date using the provided language.
            """
    )
    @GetMapping
    public ScoreResponse score(
            @Parameter(
                    description = "The main programming language of the repository",
                    example = "java"
            )
            @RequestParam
            String language,
            @Parameter(
                    description = "The earliest created date for a repository to be scored.",
                    example = "2025-12-08"
            )
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate earliestCreatedDate
    ) {
        List<RepositoryScore> scoredRepositories = scoreService.getScoredRepositories(language, earliestCreatedDate);
        return new ScoreResponse(scoredRepositories);
    }
}
