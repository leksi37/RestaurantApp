package BasicClasses;

import java.io.Serializable;

public enum RequestType implements Serializable {
    ADD_ORDER,
    REMOVE_ORDER,
    CHANGE_STATE,
    GET_ORDER,
    ADD_MENU_ITEM,
    REMOVE_MENU_ITEM,
    GET_MENU_ITEMS,
    GET_TABLE_ID
}
