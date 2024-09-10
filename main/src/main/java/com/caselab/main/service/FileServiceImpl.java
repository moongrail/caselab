package com.caselab.main.service;

import com.caselab.main.dto.CreateFileRequest;
import com.caselab.main.dto.FileResponse;
import com.caselab.main.exception.FileNotExistException;
import com.caselab.main.mapper.FileMapperUtil;
import com.caselab.main.model.File;
import com.caselab.main.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.caselab.main.mapper.FileMapperUtil.fromCreateDtoFile;
import static com.caselab.main.mapper.FileMapperUtil.toDtoResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    @Override
    public Long createFile(CreateFileRequest fileInfo) {
        File save = fileRepository.save(fromCreateDtoFile(fileInfo));
        return save.getId();
    }

    @Override
    public FileResponse getFileById(Long id) {
        File file = fileRepository.findById(id).orElseThrow(() ->
                new FileNotExistException(String.format("File with id = %s not exist", id)));
        return toDtoResponse(file);
    }

    @Override
    public List<FileResponse> getFiles(int from, int size) {
        PageRequest pagingSort = PageRequest.of(from, size, Sort.Direction.DESC, "creationDate");
        Page<File> files = fileRepository.findAll(pagingSort);

        return files.getContent().stream()
                .map(FileMapperUtil::toDtoResponse)
                .collect(Collectors.toList());
    }
}
