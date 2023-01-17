package platform.codingnomads.co.springweb.gettingdatafromclient.handlingmultipartdata.models;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse{

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}