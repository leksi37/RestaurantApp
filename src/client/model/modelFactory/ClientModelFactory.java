package client.model.modelFactory;

import basicClasses.clients;
import client.model.chef.ChefModelImpl;
import client.model.customer.CustomerModelImpl;
import client.model.waiter.WaiterModelImpl;

import java.util.HashMap;

public class ClientModelFactory {

    private static HashMap<String, Object> models= new HashMap<>();

    public static Object getModel(clients clientName){
        Object model;
        model=models.get(clientName.toString());

        if(model==null)
        {
            //FOR SOME REASON IT ALWAYS CREATES CUSTOMER CLIENT + if you try to create waiter or chef their model after customer's
            if(clientName.equals(clients.CUSTOMER_CLIENT)){
                model= new CustomerModelImpl();
                System.out.println("customer model created (ClientModelFactory)");
                models.put(String.valueOf(clients.CUSTOMER_CLIENT), model);
            }
            else if(clientName.equals(clients.CHEF_CLIENT)){
                model = new ChefModelImpl();
                models.put(String.valueOf(clients.CHEF_CLIENT), model);
            }
            else if(clientName.equals(clients.WAITER_CLIENT)){
                model = new WaiterModelImpl();
                System.out.println("Waiter Model created (ClientModelFactory)");
                models.put(String.valueOf(clients.WAITER_CLIENT), model);
            }

                // ADD ONE FOR MANAGER
            }
        return model;
    }
}
