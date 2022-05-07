package com.prgrms.restapiclone.repository;

import com.prgrms.restapiclone.Category;
import com.prgrms.restapiclone.entity.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PartJdbcRepository implements PartRepository {

    private static final String INSERT_SQL = "INSERT INTO part(name, price, category) VALUES(:name, :price, :category)";
    public static final String SELECT_BY_ID_SQL = "SELECT * FROM part WHERE part_id = :partId";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Long save(Part part) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", part.getName())
                .addValue("price", part.getPrice())
                .addValue("category", part.getCategory().name());

        jdbcTemplate.update(INSERT_SQL, parameterSource, keyHolder, new String[]{"part_id"});

        return keyHolder.getKeyAs(BigInteger.class).longValue();
    }

    @Override
    public Optional<Part> findById(Long computerPartId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, Collections.singletonMap("partId", computerPartId), computerPartRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Part> findByCategory(Category category) {
        String selectSql = findByCategoryQueryBuilder(category);
        return jdbcTemplate.query(selectSql, computerPartRowMapper);
    }

    private String findByCategoryQueryBuilder(Category category) {
        StringBuilder sb = new StringBuilder("SELECT * FROM part");

        if (category != Category.NONE) {
            sb.append(" WHERE category = '").append(category.name()).append("'");
        }

        return sb.toString();
    }

    private final RowMapper<Part> computerPartRowMapper = (rs, rowNum) -> {
        Long partId = rs.getLong("part_id");
        String name = rs.getString("name");
        long price = rs.getLong("price");
        Category category = Category.valueOf(rs.getString("category"));
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        return Part.of(partId, name, category, price, createdAt);
    };
}
