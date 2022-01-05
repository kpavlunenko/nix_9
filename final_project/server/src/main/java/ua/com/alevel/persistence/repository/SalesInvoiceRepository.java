package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.document.SalesInvoice;

@Repository
public interface SalesInvoiceRepository extends AbstractRepository<SalesInvoice> {
}
