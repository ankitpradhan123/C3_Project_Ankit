import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();




    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        int value1 = getCurrentTime().compareTo(this.openingTime);
        int value2 = getCurrentTime().compareTo(this.closingTime);
        if(value1>=0 && value2<=0)
            return true;
        else
            return false;
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {

        return Collections.unmodifiableList(menu);
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

//    Calculate sum total price of the selected items

    int calculateSum = 0;
    List<Item> selectedItems = new ArrayList<>();

    public Integer selectItem(String itemName) {

        for(Item selectedItem: menu) {
            if (selectedItem.getName().equals(itemName)) {
                selectedItems.add(selectedItem);
                calculateSum = calculateSum + selectedItem.getPrice();
                return calculateSum;
            }
        }

        return null;
    }

//    Calculate sum total after unchecking the items from the selected list

    public Integer removeItem(String itemName){

        for(Item unselectedItem: selectedItems){
            if(unselectedItem.getName().equals(itemName)){
                selectedItems.remove(itemName);
                calculateSum = calculateSum - unselectedItem.getPrice();
                return calculateSum;
            }

        }

        return null;
    }



    public String getName() {
        return name;
    }

}
