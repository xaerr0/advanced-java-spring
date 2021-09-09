package platform.codingnomads.co.springweb.gettingdatafromclient.handlingmultipartdata;

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

    @Column
    private String fileName;
    @Column
    private String fileType;

    @Column
    @Lob
    private byte[] data;
}
