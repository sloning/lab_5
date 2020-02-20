package lab;
import commands.*;
import input_output.InputOutput;
public class Main {
    public static void main(String[] args) throws Exception {
            Clear clear = new Clear();
            Count_by_genre count_by_genre = new Count_by_genre();
            Execute_script execute_script = new Execute_script();
            Exit exit = new Exit();
            Filter_starts_with_name filter_starts_with_name = new Filter_starts_with_name();
            Help help = new Help();
            History history = new History();
            Info info = new Info();
            Insert_key insert_key = new Insert_key();
            Min_by_id min_by_id = new Min_by_id();
            Remove_if_lowe remove_if_lowe = new Remove_if_lowe();
            Remove_key remove_key = new Remove_key();
            Replace_if_lowe replace_if_lowe = new Replace_if_lowe();
            Save save = new Save();
            Show show = new Show();
            Update_id update_id = new Update_id();

        while (true) {
            InputOutput inputOutput = new InputOutput();
            inputOutput.Input();
        }
    }
}