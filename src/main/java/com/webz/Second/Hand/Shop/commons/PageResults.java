package com.webz.Second.Hand.Shop.commons;

import java.util.List;

public interface PageResults<E> {
    List<E> getResults();

    Integer getPageIndex();

    Integer getPageSize();

    Long getTotalElements();
}