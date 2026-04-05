package com.demandforecast.config;

import com.demandforecast.entity.Product;
import com.demandforecast.entity.Sale;
import com.demandforecast.repository.ProductRepository;
import com.demandforecast.repository.SaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {

        // deixa o histórico de vendas REAL vindo do Angular
        System.out.println("➡ Nenhum histórico gerado automaticamente.");

        if (productRepository.count() == 0) {
            System.out.println("📦 Criando produtos iniciais...");

            List<Product> products = List.of(
                    new Product(null, "iPhone 14 Pro Max", "Smartphone Premium", 6999.00),
                    new Product(null, "Samsung Smart TV 65\"", "Televisor 4K", 4999.00),
                    new Product(null, "Notebook Gamer RTX 4060", "Notebook Gamer", 8999.00),
                    new Product(null, "PlayStation 5", "Console", 3999.00),
                    new Product(null, "Xbox Series X", "Console", 3899.00)
            );

            productRepository.saveAll(products);

            System.out.println("✔ Produtos criados.");
        } else {
            System.out.println("✔ Produtos já existem. Nenhuma alteração feita.");
        }
    }
}
