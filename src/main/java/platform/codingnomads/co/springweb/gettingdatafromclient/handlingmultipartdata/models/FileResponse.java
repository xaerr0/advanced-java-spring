package platform.codingnomads.co.springweb.gettingdatafromclient.handlingmultipartdata.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileResponse{

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
