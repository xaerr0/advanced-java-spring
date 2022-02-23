package platform.codingnomads.co.springdata.example.querydsl.repository;

import platform.codingnomads.co.springdata.example.querydsl.models.Area;

public interface AreaCustomRepository {
    Area findByCode(String code);
}
