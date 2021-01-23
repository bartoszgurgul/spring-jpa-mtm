package pl.javastart.springjpamtm.dao;

import org.springframework.stereotype.Repository;
import pl.javastart.springjpamtm.model.Product;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ProductDao extends GenericDao<Product, Long> {
}
