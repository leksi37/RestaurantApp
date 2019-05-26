package tests;

import basicClasses.ClientType;
import client.model.chef.ChefModel;
import client.model.logIn.modelFactory.ClientModel;
import client.model.logIn.modelFactory.ClientModelFactory;
import client.model.waiter.WaiterModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientModelFactoryTest {

   //because we are testing static method we don't need to initialize an object

    @Test
    public void getClientModel(){
        ClientModel m= ClientModelFactory.getModel(ClientType.CUSTOMER_CLIENT);
        assertFalse(m==null);
    }

    @Test
    public void checkClientModel(){
        ClientModel m= ClientModelFactory.getModel(ClientType.CUSTOMER_CLIENT);
        assertTrue(m instanceof ClientModel);
    }

    @Test
    public void checkClientModelSame(){
        ClientModel m= ClientModelFactory.getModel(ClientType.CUSTOMER_CLIENT);
        ClientModel mo= ClientModelFactory.getModel(ClientType.CUSTOMER_CLIENT);
        assertTrue(m.equals(mo));
    }

    @Test
    public void getChefModel(){
        ClientModel m= ClientModelFactory.getModel(ClientType.CHEF_CLIENT);
        assertFalse(m==null);
    }

    @Test
    public void checkChefModel(){
        ClientModel m= ClientModelFactory.getModel(ClientType.CHEF_CLIENT);
        assertTrue(m instanceof ChefModel);
    }

    @Test
    public void checkChefModelSame(){
        ClientModel m= ClientModelFactory.getModel(ClientType.CHEF_CLIENT);
        ClientModel mo= ClientModelFactory.getModel(ClientType.CHEF_CLIENT);
        assertTrue(m.equals(mo));
    }

    @Test
    public void checkChefModelNotCustomerModel(){
        ClientModel m= ClientModelFactory.getModel(ClientType.CHEF_CLIENT);
        ClientModel mo= ClientModelFactory.getModel(ClientType.CUSTOMER_CLIENT);
        assertFalse(m.equals(mo));
    }

    @Test
    public void getWaiterModel(){
        ClientModel m= ClientModelFactory.getModel(ClientType.WAITER_CLIENT);
        assertFalse(m==null);
    }

    @Test
    public void checkWaiterModel(){
        ClientModel m= ClientModelFactory.getModel(ClientType.WAITER_CLIENT);
        assertTrue(m instanceof WaiterModel);
    }

    @Test
    public void checkWaiterModelSame(){
        ClientModel m= ClientModelFactory.getModel(ClientType.WAITER_CLIENT);
        ClientModel mo= ClientModelFactory.getModel(ClientType.WAITER_CLIENT);
        assertTrue(m.equals(mo));
    }

    @Test
    public void checkWaiterModelNotCustomerModel(){
        ClientModel m= ClientModelFactory.getModel(ClientType.WAITER_CLIENT);
        ClientModel mo= ClientModelFactory.getModel(ClientType.CUSTOMER_CLIENT);
        assertFalse(m.equals(mo));
    }

    @Test
    public void checkWaiterModelNotChefModel(){
        ClientModel m= ClientModelFactory.getModel(ClientType.CHEF_CLIENT);
        ClientModel mo= ClientModelFactory.getModel(ClientType.CUSTOMER_CLIENT);
        assertFalse(m.equals(mo));
    }
}