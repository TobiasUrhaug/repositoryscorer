package org.urhaug.repositoryscorer.scorer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.urhaug.repositoryscorer.scorer.dto.ScoreResponse;

import java.time.LocalDate;

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
            description = "Takes a language and a created date and returns repository scores."
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
                    description = "Filter repositories created on or after this date",
                    example = "2025-12-08"
            )
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate createdDate
    ) {
        return scoreService.getScoredRepositories(language, createdDate);
    }
}
