package platform.codingnomads.co.springweb.gettingdatafromclient.handlingmultipartdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "database_files")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DatabaseFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String fileName;
    @Column
    private String fileType;

    @Column
    @Lob
    private byte[] data;

}
