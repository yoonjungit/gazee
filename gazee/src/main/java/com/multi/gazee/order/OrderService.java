package com.multi.gazee.order;

import org.springframework.ui.Model;

public interface OrderService {
    String getOrderList(Model model) throws Exception;
}
