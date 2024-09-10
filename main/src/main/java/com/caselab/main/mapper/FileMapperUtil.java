package com.caselab.main.mapper;

import com.caselab.main.dto.CreateFileRequest;
import com.caselab.main.dto.FileResponse;
import com.caselab.main.model.File;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileMapperUtil {
    public static FileResponse toDtoResponse(File file){
        return FileResponse.builder()
                .id(file.getId())
                .title(file.getTitle())
                .description(file.getDescription())
                .creationDate(file.getCreationDate())
                .content(file.getContent())
                .build();
    }

    public static File fromCreateDtoFile(CreateFileRequest file){
        return File.builder()
                .title(file.title())
                .description(file.description() == null ? "No description" : file.description())
                .creationDate(file.creationDate())
                .content(file.content())
                .build();
    }
}
