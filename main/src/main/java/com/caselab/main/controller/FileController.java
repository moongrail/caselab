package com.caselab.main.controller;

import com.caselab.main.dto.CreateFileRequest;
import com.caselab.main.dto.FileResponse;
import com.caselab.main.service.FileService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
@Validated
public class FileController {
    private final FileService fileService;

    @GetMapping("/{id}")
    public FileResponse getFile(@Positive @PathVariable Long id) {
        return fileService.getFileById(id);
    }

    @GetMapping()
    public List<FileResponse> getFilesByCreatedDate(@RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                                    @RequestParam(defaultValue = "10") @Positive int size) {
        return fileService.getFiles(from, size);
    }

    @PostMapping
    public Long createFile(@Valid @RequestBody CreateFileRequest fileInfo) {
        return fileService.createFile(fileInfo);
    }
}
