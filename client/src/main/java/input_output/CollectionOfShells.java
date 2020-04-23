package input_output;

import data.Shell;

import java.util.ArrayList;
import java.util.List;

public class CollectionOfShells {
    private static List<Shell> ShellCollection = new ArrayList<>();

    public void addShell(Shell shell){
        ShellCollection.add(shell);
    }
    public List<Shell> getShellCollection(){
        return ShellCollection;
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
