package com.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                //Inventory-Service
                .route("inventory_get_inventories", r -> r.path("/inventory")
                        .and().query("itemCode")
                        .uri("lb://Inventory-Service"))
                .route("inventory_get_by_item_code", r -> r.path("/inventory/{itemCode}")
                        .uri("lb://Inventory-Service"))
                .route("inventory_create", r -> r.path("/inventory")
                        .uri("lb://Inventory-Service"))
                .route("inventory_add_quantity", r -> r.path("/inventory/{itemCode}/add/{quantity}")
                        .uri("lb://Inventory-Service"))
                .route("inventory_subtract_quantity", r -> r.path("/inventory/{itemCode}/subtract/{quantity}")
                        .uri("lb://Inventory-Service"))
                .route("inventory_update", r -> r.path("/inventory/{itemCode}")
                        .uri("lb://Inventory-Service"))
                .route("inventory_delete", r -> r.path("/inventory/{itemCode}")
                        .uri("lb://Inventory-Service"))

                //Product-Service
                .route("products_get_all", r -> r.path("/products/all")
                        .uri("lb://Product-Service"))
                .route("products_get_products", r -> r.path("/products")
                        .and().query("names")
                        .uri("lb://Product-Service"))
                .route("products_get_by_id", r -> r.path("/products/{id}")
                        .uri("lb://Product-Service"))
                .route("products_create", r -> r.path("/products")
                        .uri("lb://Product-Service"))
                .route("products_update", r -> r.path("/products/{name}")
                        .uri("lb://Product-Service"))
                .route("products_delete", r -> r.path("/products/{name}")
                        .uri("lb://Product-Service"))

                //Order-Service
                .route("orders_get_all", r -> r.path("/orders")
                        .uri("lb://Order-Service"))
                .route("orders_get_by_id", r -> r.path("/orders/{id}")
                        .uri("lb://Order-Service"))
                .route("orders_create", r -> r.path("/orders")
                        .uri("lb://Order-Service"))
                .route("orders_update", r -> r.path("/orders/{id}")
                        .uri("lb://Order-Service"))
                .route("orders_delete", r -> r.path("/orders/{id}")
                        .uri("lb://Order-Service"))

                //User-Service
                .route("user_sign_in", r -> r.path("/auth/signin")
                        .uri("lb://User-Service"))
                .route("user_sign_up", r -> r.path("/auth/signup")
                        .uri("lb://User-Service"))

                //OpenAPI
                .route("user_open_api", r -> r.path("/user-service/v3/api-docs")
                        .uri("lb://User-Service"))
                .route("product_open_api", r -> r.path("/product-service/v3/api-docs")
                        .uri("lb://Product-Service"))
                .route("order_open_api", r -> r.path("/order-service/v3/api-docs")
                        .uri("lb://Order-Service"))
                .route("inventory_open_api", r -> r.path("/inventory-service/v3/api-docs")
                        .uri("lb://Inventory-Service"))
                //swagger-ui
                .route("user_swagger-ui.html", r -> r.path("/user-service/swagger-ui.html")
                        .uri("lb://User-Service"))
                .route("product_swagger-ui.html", r -> r.path("/product-service/swagger-ui.html")
                        .uri("lb://Product-Service"))
                .route("order_swagger-ui.html", r -> r.path("/order-service/swagger-ui.html")
                        .uri("lb://Order-Service"))
                .route("inventory_swagger-ui.html", r -> r.path("/inventory-service/swagger-ui.html")
                        .uri("lb://Inventory-Service"))


                .build();
    }
}
