package platform.codingnomads.co.springdata.example.mybatis.usingmappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import platform.codingnomads.co.springdata.example.mybatis.usingmappers.mappers.SongMapper;
import platform.codingnomads.co.springdata.example.mybatis.usingmappers.models.Song;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    SongMapper songMapper;

    @Transactional(rollbackFor = Exception.class)
    public void doSmth() throws Exception {
        Song s = songMapper.getSongById(14L);
        songMapper.insertNewSong(s);
        throw new Exception();
    }
}
