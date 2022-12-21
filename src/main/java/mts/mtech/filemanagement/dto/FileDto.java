package mts.mtech.filemanagement.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import mts.mtech.filemanagement.model.File;

import java.util.Date;

/**
 * @author Mitch
 *
 */

public class FileDto {
    private Long id;
    private Long userId;
    private String fileName;
    private Long contentId;
    private String contentType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date datePosted;

    public FileDto() {
    }

    private FileDto(Long id, Long userId, String fileName, Long contentId, String contentType, Date datePosted) {
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.contentId = contentId;
        this.contentType = contentType;
        this.datePosted = datePosted;
    }

    public static FileDto of(File file) {
        return new FileDto(file.getId(),
                    file.getUserId(),
                    file.getFileName(),
                    file.getContentId(),
                    file.getContentType(),
                    file.getDatePosted());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", fileName='" + fileName + '\'' +
                ", contentId=" + contentId +
                ", contentType='" + contentType + '\'' +
                ", datePosted=" + datePosted +
                '}';
    }
}


