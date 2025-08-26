import java.util.ArrayList;
import java.util.List;

/**
 * Manages a task list and handles persistence using DataHandler.
 */
class ChatList {
    private final String filename;
    private final DataHandler dataHandler;

    /**
     * Constructs a ChatList using a CSV filename.
     *
     * @param filename the CSV file to store tasks.
     */
    ChatList(String filename) {
        this.filename = filename;
        this.dataHandler = new DataHandler();
    }

    /**
     * Loads all tasks from the CSV file.
     *
     * @return a list of Item objects.
     */
    private List<Item> loadItems() {
        List<Item> items = new ArrayList<>();
        List<String[]> rows = dataHandler.readFile(filename);
        for (String[] row : rows) {
            Item item = Item.fromCSVRow(row);
            if (item != null) items.add(item);
        }
        return items;
    }

    /**
     * Saves a list of tasks to the CSV file.
     *
     * @param items the list of tasks to save.
     */
    private void saveItems(List<Item> items) {
        List<String[]> rows = new ArrayList<>();
        for (Item item : items) {
            rows.add(item.toCSVRow());
        }
        dataHandler.writeToFile(filename, rows);
    }

    /**
     * Adds a new task to the list and persists it.
     *
     * @param item the task to add.
     */
    void addToList(Item item) {
        if (!item.checkValid()) {
            System.out.println("Something went wrong master");
            return;
        }
        List<Item> items = loadItems();
        items.add(item);
        saveItems(items);
        System.out.println("I've added \n" + item + "\n to your list master");
        System.out.println("You now have " + items.size() + " items in your list");
    }

    /**
     * Deletes a task by its index in the list.
     *
     * @param input the index (1-based) of the task to delete.
     */
    void deleteFromList(String input) {
        try {
            int index = Integer.parseInt(input) - 1;
            List<Item> items = loadItems();
            Item removed = items.remove(index);
            saveItems(items);
            System.out.println("I've removed \n" + removed + "\n from your list master");
            System.out.println("You now have " + items.size() + " items in your list");
        } catch (NumberFormatException e) {
            System.out.println("Please provide me with a number master");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please provide me a number smaller than " + loadItems().size());
        }
    }

    /**
     * Marks a task as completed by its index.
     *
     * @param index 1-based index of the task to mark.
     */
    void mark(int index) {
        try {
            index--;
            List<Item> items = loadItems();
            items.get(index).mark();
            saveItems(items);
            System.out.println("I have marked this as completed master");
            System.out.println("   " + items.get(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please give a number between 1 and " + loadItems().size() + " master");
        } finally {
            System.out.println("Example: mark 2");
        }
    }

    /**
     * Unmarks a task as not completed by its index.
     *
     * @param index 1-based index of the task to unmark.
     */
    void unmark(int index) {
        try {
            index--;
            List<Item> items = loadItems();
            items.get(index).unmark();
            saveItems(items);
            System.out.println("I have unmarked this from your list master");
            System.out.println("   " + items.get(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please give a number between 1 and " + loadItems().size() + " master");
        } finally {
            System.out.println("Example: unmark 2");
        }
    }

    /**
     * Returns a formatted string listing all tasks.
     *
     * @return string representation of all tasks.
     */
    @Override
    public String toString() {
        List<Item> items = loadItems();
        StringBuilder sb = new StringBuilder("Here is your list master:\n");
        for (int i = 1; i <= items.size(); i++) {
            sb.append(i).append(". ").append(items.get(i - 1)).append("\n");
        }
        return sb.toString();
    }
}
