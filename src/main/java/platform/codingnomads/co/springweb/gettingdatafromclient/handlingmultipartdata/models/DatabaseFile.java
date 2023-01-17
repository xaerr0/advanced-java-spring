package platform.codingnomads.co.springweb.gettingdatafromclient.handlingmultipartdata.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "database_files")
public class DatabaseFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    @Transient
    private String downloadUrl;

    public DatabaseFile(String fileName, String fileType, byte[] data, String downloadUrl) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.downloadUrl = downloadUrl;
    }
}