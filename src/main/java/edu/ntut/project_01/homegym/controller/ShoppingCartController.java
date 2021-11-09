package edu.ntut.project_01.homegym.controller;

import ecpay.payment.integration.AllInOne;
import example.ExampleAllInOne;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ShoppingCartController {

    private static AllInOne all;

//測試OK
/*
使用方法：post過來的參數要有price(總價)orderItems(買得項目)
{
    "price":"2580",
    "orderItems":"BodyCombat 88 $580 # 操爆你的二頭肌 $500 # 瑜珈60 $580"
}
使用#來分開多個項目
*/
    @PostMapping("/test/buySoomething")
    ResponseEntity<String> useECPAY(@RequestBody Map<String,String> checkOut) {
        ExampleAllInOne exampleAllInOne = new ExampleAllInOne();
        ExampleAllInOne.initial();
        String price = checkOut.get("price");
        String orderItems = checkOut.get("orderItems");
        String paymentPage = exampleAllInOne.genAioCheckOutALL(price, orderItems);
        if (paymentPage != null) {
            return ResponseEntity.ok().body(paymentPage);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("交易失敗");
    }
}
