package client.model.modelFactory;

import basicClasses.clients;
import client.model.chef.ChefModelImpl;
import client.model.customer.CustomerModelImpl;
import client.model.waiter.WaiterModelImpl;

import java.util.HashMap;

public class ClientModelFactory {

    private static HashMap<String, Object> models= new HashMap<>();

    public static Object getModel(clients clientName){
        Object model=null;
           model=models.get(clientName.toString());
        if(model==null)
        {
            if(clientName==clients.CUSTOMER_CLIENT){
                model= new CustomerModelImpl();
                System.out.println("customer model created");
                models.put(String.valueOf(clients.CUSTOMER_CLIENT), model);
            }
            else if(clientName==clients.CHEF_CLIENT){
                model = new ChefModelImpl();
                models.put(String.valueOf(clients.CHEF_CLIENT), model);
            }
            else if(clientName==clients.WAITER_CLIENT){
                model = new WaiterModelImpl();
                models.put(String.valueOf(clients.WAITER_CLIENT), model);
            }

                // ADD ONE FOR MANAGER
            }
        return model;
    }
}
