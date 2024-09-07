package com.sankalp.shoppingServer.service.product;

import com.sankalp.shoppingServer.model.Category;
import com.sankalp.shoppingServer.model.Product;
import com.sankalp.shoppingServer.requests.AddProductRequest;
import com.sankalp.shoppingServer.requests.UpdateProductRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest request);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(UpdateProductRequest request, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(Category category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    public Long countProductsByBrandAndName(String brand, String name);
}


