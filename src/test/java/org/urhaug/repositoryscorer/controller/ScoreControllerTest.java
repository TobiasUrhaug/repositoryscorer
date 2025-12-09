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
        ScoreResponse mockResponse = new ScoreResponse(
                List.of(
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
                )
        );

        when(scoreService
                    .getScoredRepositories("java", LocalDate.of(2022, 12, 17)))
                    .thenReturn(mockResponse);

        mockMvc.perform(get("/score")
                        .param("language", "java")
                        .param("createdDate", "2022-12-17"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.repositories").isArray())
                .andExpect(jsonPath("$.repositories[0].name").value("repo-one"))
                .andExpect(jsonPath("$.repositories[0].score").value(87.5))
                .andExpect(jsonPath("$.repositories[0].language").value("Java"))
                .andExpect(jsonPath("$.repositories[0].createdDate").value("2023-01-15"))
                .andExpect(jsonPath("$.repositories[1].name").value("repo-two"))
                .andExpect(jsonPath("$.repositories[1].score").value(92.0))
                .andExpect(jsonPath("$.repositories[1].score").value(92.0))
                .andExpect(jsonPath("$.repositories[1].createdDate").value("2025-03-23"))
                .andExpect(jsonPath("$.repositories[1].language").value("Kotlin"));
    }
}