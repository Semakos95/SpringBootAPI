package gr.cinema.api.dto;

public class ImageDTO {

    private Long id;
    private String fileName;
    private String fileType;
    private byte[] bytes;
    private PerformanceDTO performanceDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public PerformanceDTO getPerformanceDTO() {
        return performanceDTO;
    }

    public void setPerformanceDTO(PerformanceDTO performanceDTO) {
        this.performanceDTO = performanceDTO;
    }
}
