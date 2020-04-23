package data;

import data.Shell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FabricOfShell implements Serializable {
    private static List<Shell> ShellCollection = new ArrayList<>();

    public void addShell(Shell shell){
        ShellCollection.add(shell);
    }

    public void clearCollection(){
        ShellCollection.clear();
    }

    public Shell getShell(int i){
        return ShellCollection.get(i);
    }

    public int getSize(){
        return ShellCollection.size();
    }
}
