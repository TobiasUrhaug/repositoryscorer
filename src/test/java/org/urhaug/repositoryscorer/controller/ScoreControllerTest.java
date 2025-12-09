package org.urhaug.repositoryscorer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.urhaug.repositoryscorer.scorer.RepositoryScore;
import org.urhaug.repositoryscorer.scorer.ScoreService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScoreController.class)
class ScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ScoreService scoreService;

    @Test
    void testScoreEndpoint() throws Exception {
        List<RepositoryScore> mockRepositoryScores = List.of(
                new RepositoryScore(
                        "repo-one",
                        "Java",
                        LocalDate.of(2023, 1, 15),
                        87.5
                ),
                new RepositoryScore(
                        "repo-two",
                        "Kotlin",
                        LocalDate.of(2025, 3, 23),
                        92.0
                )
        );

        when(scoreService
                    .getScoredRepositories("java", LocalDate.of(2022, 12, 17)))
                    .thenReturn(mockRepositoryScores);

        mockMvc.perform(get("/score")
                        .param("language", "java")
                        .param("earliestCreatedDate", "2022-12-17"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.repositoryScores").isArray())
                .andExpect(jsonPath("$.repositoryScores[0].name").value("repo-one"))
                .andExpect(jsonPath("$.repositoryScores[0].score").value(87.5))
                .andExpect(jsonPath("$.repositoryScores[0].language").value("Java"))
                .andExpect(jsonPath("$.repositoryScores[0].createdDate").value("2023-01-15"))
                .andExpect(jsonPath("$.repositoryScores[1].name").value("repo-two"))
                .andExpect(jsonPath("$.repositoryScores[1].score").value(92.0))
                .andExpect(jsonPath("$.repositoryScores[1].score").value(92.0))
                .andExpect(jsonPath("$.repositoryScores[1].createdDate").value("2025-03-23"))
                .andExpect(jsonPath("$.repositoryScores[1].language").value("Kotlin"));
    }
}