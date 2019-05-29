package client.model.modelFactory;

import basicClasses.ClientType;
import client.model.chef.ChefModelImpl;
import client.model.customer.CustomerModelImpl;
import client.model.waiter.WaiterModelImpl;

import java.util.HashMap;

public class ClientModelFactory {

    private static HashMap<String, Object> models= new HashMap<>();

    public static Object getModel(ClientType clientName){
        Object model=null;
           model=models.get(clientName.toString());
        if(model==null)
        {
            if(clientName==ClientType.CUSTOMER_CLIENT){
                model= new CustomerModelImpl();
                System.out.println("customer model created");
                models.put(String.valueOf(ClientType.CUSTOMER_CLIENT), model);
            }
            else if(clientName==ClientType.CHEF_CLIENT){
                model = new ChefModelImpl();
                models.put(String.valueOf(ClientType.CHEF_CLIENT), model);
            }
            else if(clientName==ClientType.WAITER_CLIENT){
                model = new WaiterModelImpl();
                models.put(String.valueOf(ClientType.WAITER_CLIENT), model);
            }

                // ADD ONE FOR MANAGER
            }
        return model;
    }
}
