package basicClasses;

import java.io.Serializable;

public enum RequestType implements Serializable {
    ADD_ORDER,
    GET_MENU_ITEMS,
    GET_TABLE_ID,
    SEND_NOTIFICATION,
    GET_CONNECTION_ID,
    WAITER_PASSWORD_CHECK,
    CHEF_PASSWORD_CHECK,
    CHEF_APPROVED,
    CHEF_DISAPPROVED,
    NEW_ORDER,
    ADDED_TO_ORDER,
    FETCH_ORDERS;
}
