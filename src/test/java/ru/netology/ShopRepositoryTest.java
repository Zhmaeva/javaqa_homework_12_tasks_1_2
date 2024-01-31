package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.domain.exception.AlreadyExistsException;
import ru.netology.domain.exception.NotFoundException;
import ru.netology.repositiry.ShopRepository;

public class ShopRepositoryTest {

    Product product1 = new Product(1, "PlayStation 5", 50_000);
    Product product2 = new Product(10, "Xbox Series X", 49_999);
    Product product3 = new Product(7, "Nintendo Switch", 29_900);
    Product product4 = new Product(7, "IPhone 15", 120_000);

    @Test
    public void shouldRemoveById() {
        ShopRepository repository = new ShopRepository();
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        repository.removeById(product2.getId());

        Product[] expected = {product1, product3};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGenerateExceptionById() {
        ShopRepository repository = new ShopRepository();
        repository.add(product1);
        repository.add(product2);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(2);
        });
    }

    @Test
    public void shouldAddProduct() {
        ShopRepository repository = new ShopRepository();
        repository.add(product1);
        repository.add(product4);

        Product[] expected = {product1, product4};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotAddProductById() {
        ShopRepository repository = new ShopRepository();
        repository.add(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.add(product4);
        });
    }
}
