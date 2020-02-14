package commands;
import product.*;
public class add implements ICommand{
    public add(Commands com){
        com.addNewCommand("add",this);
    }

    @Override
    public void Do(String parameter){
        ProductCollection.addProduct(parameter);
    }
}
