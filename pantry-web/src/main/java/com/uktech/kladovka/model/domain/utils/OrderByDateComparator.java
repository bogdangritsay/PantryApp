package com.uktech.kladovka.model.domain.utils;

import com.uktech.kladovka.model.domain.Order;

import java.util.Comparator;

public class OrderByDateComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        if (o1.getDateOfSubmit().isAfter(o2.getDateOfSubmit())) {
            return -1;
        } else if (o1.getDateOfSubmit().isBefore(o2.getDateOfSubmit())) {
            return 1;
        } else return 0;
    }
}
