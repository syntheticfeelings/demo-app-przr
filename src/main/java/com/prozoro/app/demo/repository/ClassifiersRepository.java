package com.prozoro.app.demo.repository;

import com.prozoro.app.demo.domain.SectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


@Repository
public class ClassifiersRepository {

    private static final String UPDATE_SECTION = "INSERT INTO app_section (id , description, parent) " +
            "VALUES (?,?,?) " +
            "ON CONFLICT (id) DO UPDATE SET description = EXCLUDED.description, parent = EXCLUDED.parent;";

    private static final String UPDATE_GROUP = "INSERT INTO app_group (id, section_id, description) " +
            "VALUES (?,?,?) " +
            "ON CONFLICT (id) DO UPDATE SET section_id = EXCLUDED.section_id, description = EXCLUDED.description;";

    private static final String FIND_SECTION = "SELECT id, description " +
            "FROM app_section WHERE id=?;";

    private static final String FIND_SECTION_BY_PARENT = "SELECT id, description " +
            "FROM app_section WHERE parent=?;";

    private static final String FIND_ALL_SECTION =
            "SELECT id, description, parent FROM app_section";

    private static final String FIND_GROUPS_BY_SECTION_CODE =
            "SELECT id, section_id, description " +
                    "FROM public.app_group WHERE section_id =?;";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional
    public int[][] batchSectionUpdate(Collection<SectionDto> sections, int batchSize) {
        return jdbcTemplate.batchUpdate(UPDATE_SECTION, sections, batchSize, this::updateSectionDto);
    }


    @Transactional
    public List<SectionDto> findAllSectionDto() {
        return jdbcTemplate.query(FIND_ALL_SECTION, (rs, i) -> SectionDto.builder()
                .id(rs.getString("id"))
                .description(rs.getString("description"))
                .parent(rs.getString("parent"))
                .build());
    }

    @Transactional
    public SectionDto findSectionDto(String sectionId) {
        return jdbcTemplate.queryForObject(FIND_SECTION, (rs, i) -> SectionDto.builder()
                .id(rs.getString("id"))
                .description(rs.getString("description"))
                .build(), sectionId);
    }

    @Transactional
    public SectionDto findSectionDtoByParent(String parentId) {
        return jdbcTemplate.queryForObject(FIND_SECTION_BY_PARENT, (rs, i) -> SectionDto.builder()
                .id(rs.getString("id"))
                .description(rs.getString("description"))
                .build(), parentId);
    }


    private void updateSectionDto(PreparedStatement ps, SectionDto argument) throws SQLException {
        ps.setString(1, argument.getId());
        ps.setString(2, argument.getDescription());
        ps.setString(3, argument.getParent());
    }


}
