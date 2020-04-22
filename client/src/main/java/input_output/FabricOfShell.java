package input_output;

import data.Shell;

import java.util.ArrayList;
import java.util.List;

public class FabricOfShell {
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
