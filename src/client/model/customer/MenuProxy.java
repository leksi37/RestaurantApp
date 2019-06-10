package client.model.customer;

import basicClasses.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuProxy {

    private HashMap<String,ArrayList> checked= new HashMap<>();

    public ArrayList getCategory(String category){
        return checked.get(category);
    }

    public void addCategory(String category, ArrayList list){
        checked.put(category, list);
    }
}
