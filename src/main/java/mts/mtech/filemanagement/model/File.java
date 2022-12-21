package mts.mtech.filemanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Strings;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Mitch
 * 17/10/18
 * 11:26 PM
 */
@Entity
@Table(name = "file_storage")
@TableGenerator(name = "file_id_generator", table = "id_generator", pkColumnName = "id_name", pkColumnValue = "file", valueColumnName = "id_value", allocationSize = 50)
public class File implements Serializable{
    @Id
    @GeneratedValue(generator = "file_id_generator")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "file_name", nullable = false, unique = true)
    private String fileName;
    @Column(name = "content_id")
    private Long contentId;
    @Column(name = "content_type")
    private String contentType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date_posted", nullable = false)
    private Date datePosted;

    protected File() {
    }

    private File(String fileName) {
        this.fileName = fileName;
    }

    private File(Long userId, String fileName) {
        this.userId = userId;
        this.fileName = fileName;
    }

    public static File createFile(String fileName) {
        checkNotNull(fileName, "File name is required");
        checkArgument(!Strings.isNullOrEmpty(fileName.trim()), "File name is required");
        return new File(fileName);
    }

    public static File createUserFile(Long userId, String fileName) {
        return new File(userId, fileName);
    }

    public String getFileName() {
        return fileName;
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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        checkNotNull(contentId, "Content id is required");
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

    @PrePersist
    private void setDatePosted(){
        this.datePosted=java.sql.Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", userId=" + userId +
                ", fileName='" + fileName + '\'' +
                ", contentId=" + contentId +
                ", contentType='" + contentType + '\'' +
                ", datePosted=" + datePosted +
                '}';
    }
}
