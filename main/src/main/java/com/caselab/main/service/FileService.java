package com.caselab.main.service;

import com.caselab.main.dto.CreateFileRequest;
import com.caselab.main.dto.FileResponse;

import java.util.List;

public interface FileService {
    Long createFile(CreateFileRequest fileInfo);

    FileResponse getFileById(Long id);

    List<FileResponse> getFiles(int from, int size);
}
