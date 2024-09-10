package com.caselab.main.service;

import com.caselab.main.dto.CreateFileRequest;
import com.caselab.main.dto.FileResponse;
import com.caselab.main.exception.FileNotExistException;
import com.caselab.main.model.File;
import com.caselab.main.repository.FileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FileServiceImplTest {
    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileServiceImpl fileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createFile_ShouldReturnFileId_WhenFileIsCreated() {
        CreateFileRequest createFileRequest = CreateFileRequest.builder().build();
        File file = new File();
        file.setId(1L);

        when(fileRepository.save(any(File.class))).thenReturn(file);

        Long fileId = fileService.createFile(createFileRequest);

        assertEquals(1L, fileId);
        verify(fileRepository, times(1)).save(any(File.class));
    }

    @Test
    void getFileById_ShouldReturnFileResponse_WhenFileExists() {
        Long fileId = 1L;
        File file = new File();
        file.setId(fileId);

        when(fileRepository.findById(fileId)).thenReturn(Optional.of(file));

        FileResponse response = fileService.getFileById(fileId);

        assertNotNull(response);
        assertEquals(1L, response.id());
        verify(fileRepository, times(1)).findById(fileId);
    }

    @Test
    void getFileById_ShouldThrowFileNotExistException_WhenFileDoesNotExist() {
        Long fileId = 1L;

        when(fileRepository.findById(fileId)).thenReturn(Optional.empty());

        FileNotExistException exception = assertThrows(FileNotExistException.class, () -> fileService.getFileById(fileId));

        assertEquals("File with id = 1 not exist", exception.getMessage());
        verify(fileRepository, times(1)).findById(fileId);
    }
}