package com.caselab.main.controller;

import com.caselab.main.dto.CreateFileRequest;
import com.caselab.main.dto.FileResponse;
import com.caselab.main.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FileService fileService;

    @InjectMocks
    private FileController fileController;

    private final FileResponse fileResponse =
            FileResponse.builder()
                    .id(1L)
                    .creationDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0))
                    .description("desc")
                    .title("tit")
                    .content("sfafgsafsaf")
                    .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(fileController).build();
    }

    @Test
    void getFile_ShouldReturnFileResponse() throws Exception {

        when(fileService.getFileById(1L)).thenReturn(fileResponse);

        mockMvc.perform(get("/api/v1/files/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("tit"));

        verify(fileService, times(1)).getFileById(1L);
    }

    @Test
    void getFilesByCreatedDate_ShouldReturnListOfFileResponses() throws Exception {
        List<FileResponse> fileResponses = Collections.singletonList(fileResponse);

        when(fileService.getFiles(0, 10)).thenReturn(fileResponses);

        mockMvc.perform(get("/api/v1/files")
                        .param("from", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("tit"));

        verify(fileService, times(1)).getFiles(0, 10);
    }

    @Test
    void createFile_ShouldReturnFileId() throws Exception {
        String jsonContent = "{\n" +
                "    \"title\": \"File title\",\n" +
                "    \"creation_date\": \"2022-01-01 00:00:00\",\n" +
                "    \"description\": \"description\",\n" +
                "    \"content\": \"content\"\n" +
                "}";

        Long fileId = 1L;

        when(fileService.createFile(any(CreateFileRequest.class))).thenReturn(fileId);

        mockMvc.perform(post("/api/v1/files")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(fileId.toString()));

        verify(fileService, times(1)).createFile(any(CreateFileRequest.class));
    }
}
