package client.model.logIn.modelFactory;

import basicClasses.ClientType;
import client.model.chef.ChefModelImpl;
import client.model.customer.CustomerModelImpl;
import client.model.waiter.WaiterModelImpl;

import java.util.HashMap;

public class ClientModelFactory {

    private static HashMap<String, ClientModel> models= new HashMap<>();

    public static ClientModel getModel(ClientType clientName){
        ClientModel model;
        model=models.get(clientName.toString());

        if(model==null)
        {
            if(clientName.equals(ClientType.CUSTOMER_CLIENT)){
                model= new CustomerModelImpl();
                models.put(String.valueOf(ClientType.CUSTOMER_CLIENT), model);
            }
            else if(clientName.equals(ClientType.CHEF_CLIENT)){
                model = new ChefModelImpl();
                models.put(String.valueOf(ClientType.CHEF_CLIENT), model);
            }
            else if(clientName.equals(ClientType.WAITER_CLIENT)){
                model = new WaiterModelImpl();
                models.put(String.valueOf(ClientType.WAITER_CLIENT), model);
            }
                // ADD ONE FOR MANAGER     MANAGER_CLIENT
            }
        return model;
    }
}
