import java.util.ArrayList;

class ChatList {
    ArrayList<Item> inputs = new ArrayList<>();

    void addToList(Item item) {
        if (item.checkValid()) {
            this.inputs.add(item);
            System.out.println("I've added \n" + item + "\n to your list master");
            System.out.println("You now have " + this.inputs.size() + " items in your list");
        } else {
            System.out.println("Something went wrong master");
        }
    }

    void deleteFromList(String input) {
        try {
            int index = Integer.parseInt(input);
            index--;
            Item removed = this.inputs.get(index);
            this.inputs.remove(index);
            System.out.println("I've removed \n" + removed + "\n to your list master");
            System.out.println("You now have " + this.inputs.size() + " items in your list");
        } catch (ClassCastException e) {
            System.out.println("Please provide me with a number master");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please provide me a number smaller than " + this.inputs.size());
        }
    }

    void mark(Integer index) {
        index--;
        try {
            this.inputs.get(index).mark();
            System.out.println("I have marked this as completed master");
            System.out.println("   " + this.inputs.get(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please give a number between 1 and " + this.inputs.size() + " master");
        } catch (ClassCastException e) {
            System.out.println("Please provide a number master");
        } catch (Exception e) {
            System.out.println("I am not sure how master");
        } finally {
            System.out.println("Here's an example: \n    mark 2");
        }
    }

    void unmark(Integer index) {
        index--;
        try {
            this.inputs.get(index).unmark();
            System.out.println("I have unmarked this from your list master");
            System.out.println("   "  + this.inputs.get(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please give a number between 1 and " + this.inputs.size() + " master");
        } catch (ClassCastException e) {
            System.out.println("Please provide a number master");
        } catch (Exception e) {
            System.out.println("I am not sure how master");
        } finally {
            System.out.println("Here's an example: \n    unmark 2");
        }
    }

    @Override
    public String toString() {
        String output = "Here is your list master:\n";
        for(int i = 1; i <= this.inputs.size(); i++) {
            Item item = this.inputs.get(i - 1);
            output += i + ". " + item + "\n";
        }
        return output;
    }
}
