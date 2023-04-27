package hwr.oop.todo;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuOption> options;

    public Menu(){
        this.options = new ArrayList<>();
    }

    public Menu addOption(MenuOption option){
        options.add(option);
        return this;
    }


    public List<MenuOption> getMenuOptions(){
        return options;
    };

    protected MenuOption getOptionByKey(char key){
        MenuOption option = options.stream().filter(elem -> elem.getSelectionKey() == key).findFirst().orElse(null);

        if(option == null){
            throw new InvalidMenuOptionException("Cannot find option with selection key "+key);
        }

        return option;
    }

    public SelectionResponse getSelectionResponse(char selectionKey){
        try{
            MenuOption option = getOptionByKey(selectionKey);
            return SelectionResponse.success("You selected \""+ option.getDescription()+"\"");
        }catch(InvalidMenuOptionException e){
            return SelectionResponse.error("Selection "+selectionKey+" is invalid.");
        }
    };
}
