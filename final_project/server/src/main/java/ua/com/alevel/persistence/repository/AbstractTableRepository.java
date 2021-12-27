package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ua.com.alevel.persistence.entity.BaseTable;

@NoRepositoryBean
public interface AbstractTableRepository<ENTITY extends BaseTable> extends JpaRepository<ENTITY, Long>, JpaSpecificationExecutor<ENTITY> {
}
