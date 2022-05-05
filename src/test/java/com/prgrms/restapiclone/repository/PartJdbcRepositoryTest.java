package com.prgrms.restapiclone.repository;

import com.prgrms.restapiclone.Category;
import com.prgrms.restapiclone.config.TestConfiguration;
import com.prgrms.restapiclone.entity.Part;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(TestConfiguration.class)
class PartJdbcRepositoryTest {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    PartRepository partRepository;

    @AfterEach
    void afterEach() {
        clear();
    }

    private void clear() {
        jdbcTemplate.update("DELETE FROM orders_part", Collections.emptyMap());
        jdbcTemplate.update("DELETE FROM part", Collections.emptyMap());
    }

    @Test
    @DisplayName("ComputerPart를 저장한다.")
    void saveTest() {
        // given
        Part newPart = Part.of("intel i7", Category.CPU, 150000);

        // when
        Long newPartId = partRepository.save(newPart);

        // then
        Optional<Part> partOptional = partRepository.findById(newPartId);
        assertThat(partOptional).isNotEmpty();
        Part findPart = partOptional.get();
        assertThat(findPart).usingRecursiveComparison()
                .ignoringFields("partId", "createdAt")
                .isEqualTo(newPart);
    }

    @Test
    @DisplayName("Id로 Part를 조회한다.")
    void findByIdTest() {
        // given
        Part newPart = Part.of("intel i7", Category.CPU, 150000);
        Long newPartId = partRepository.save(newPart);

        // when
        Optional<Part> findPart = partRepository.findById(newPartId);

        // then
        assertThat(findPart).isNotEmpty();
        Part part = findPart.get();
        assertThat(part.getPartId()).isEqualTo(newPartId);
        assertThat(part).usingRecursiveComparison().ignoringFields("partId", "createdAt").isEqualTo(newPart);
    }

    @Test
    @DisplayName("존재하지 않는 Id로 Part를 조회하면 Optional.empty()가 반환된다.")
    void findByNotExistsIdTest() {
        // given
        //아무 Part도 저장하지 않는다.
        Long notExistsId = -1L;

        // when
        Optional<Part> findPart = partRepository.findById(notExistsId);

        // then
        assertThat(findPart).isEmpty();

    }

    @Test
    @DisplayName("모든 part를 조회한다.")
    void findAllTest() {
        // given
        Part newPart1 = Part.of("intel i7", Category.CPU, 150000);
        Part newPart2 = Part.of("Geforce 3060ti", Category.GPU, 1000000);
        Part newPart3 = Part.of("Samsung 32GB", Category.RAM, 50000);

        partRepository.save(newPart1);
        partRepository.save(newPart2);
        partRepository.save(newPart3);

        // when
        List<Part> parts = partRepository.findByCategory(Category.NONE);

        // then
        assertThat(parts.size()).isEqualTo(3);
        assertThat(parts).usingRecursiveFieldByFieldElementComparatorIgnoringFields("partId", "createdAt").contains(newPart1, newPart2, newPart3);
    }

    @Test
    @DisplayName("Category별로 Part를 조회한다.")
    void findByCategoryTest() {
        // given
        Part cpu = Part.of("intel i7", Category.CPU, 150000);
        Part gpu = Part.of("Geforce 3060ti", Category.GPU, 1000000);
        Part memory = Part.of("Samsung 32GB", Category.RAM, 50000);

        partRepository.save(cpu);
        partRepository.save(gpu);
        partRepository.save(memory);

        // when
        List<Part> findCpuParts = partRepository.findByCategory(Category.CPU);

        // then
        assertThat(findCpuParts.size()).isEqualTo(1);
        assertThat(findCpuParts).usingRecursiveFieldByFieldElementComparatorIgnoringFields("partId", "createdAt").contains(cpu);
    }
}