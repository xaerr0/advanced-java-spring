package platform.codingnomads.co.springdata.example.querydsl.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import platform.codingnomads.co.springdata.example.querydsl.models.Area;
import platform.codingnomads.co.springdata.example.querydsl.models.QArea;

@Repository
public class AreaCustomRepositoryImpl extends QuerydslRepositorySupport implements AreaCustomRepository {

    public AreaCustomRepositoryImpl() {
        super(Area.class);
    }

    @Override
    public Area findByCode(String code) {
        QArea area = QArea.area;
        return from(area).where(area.code.eq(code)).fetchOne();
    }
}
