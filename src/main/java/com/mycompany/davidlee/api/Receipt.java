/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.mycompany.davidlee.api;

import java.util.List;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public interface Receipt {

    /**
     * @return Currency formatted total ($X,XXX.XX) of all items
     */
    List<Item> getItems();

    double getTotal();

    double getTotalDiscount();

    void setItems(List<Item> items);

    void addItem(Item item);

    void removeItem(Item item);

    void applyCoupons(List<Item> items);
}
