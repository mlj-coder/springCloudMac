package com.mlj.order.controller;

import com.mlj.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {


    //注入rest Template对象
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 通过订单系统，调用商品服务获取（product_service）
     * @param id 商品ID
     * @return
     */
    @RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id){
        Product product = restTemplate.getForObject("http://localhost:9001/product/"+id,Product.class);
        return product;
    }
}
