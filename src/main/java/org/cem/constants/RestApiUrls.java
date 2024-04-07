package org.cem.constants;

public class RestApiUrls {

    public static final String VERSION = "api/v1";
    public static final String DEV = "dev";
    public static final String ROOT = VERSION + DEV;
    public static final String CUSTOMER = ROOT + "/customer";
    public static final String PRODUCT = ROOT + "/product";
    public static final String CART = ROOT + "/cart";
    public static final String ORDER = ROOT + "/order";


    public static final String ADD_CUSTOMER = "/add-customer";
    public static final String GET_PRODUCT = "/get-product";
    public static final String CREATE_PRODUCT = "/create-product";
    public static final String UPDATE_PRODUCT = "/update-product";
    public static final String DELETE_PRODUCT = "/delete-product";
    public static final String GET_CART = "/get-cart";
    public static final String UPDATE_CART = "/update-cart";
    public static final String EMPTY_CART = "/empty-cart";
    public static final String PLACE_ORDER = "/place-order";
    public static final String GET_ORDER_FOR_CODE = "/get-order-for-code";
    public static final String GET_ALL_ORDERS_FOR_CUSTOMER = "/get-all-orders-for-customer";
    public static final String ADD_PRODUCT_TO_CART = "/add-product-to-cart";
    public static final String REMOVE_PRODUCT_FROM_CART = "/remove-product-from-cart";
    public static final String START_ORDER_PROCESS = "/start-order-process";
}
