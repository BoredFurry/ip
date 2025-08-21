class Item {
    boolean status = false; // false == not done, true == done
    String label;

    Item(String label) {
        int firstSpace = label.split(" ")[0].length();
        int firstDate = label.split("/")[0].length();
        this.label = label.substring(firstSpace, firstDate);
    }

    String getStatusIcon() {
        return this.status ? "X" : " ";
    }

    void mark() {
        this.status = true;
    }

    void unmark() {
        this.status = false;
    }

    @Override
    public String toString() {
        return  "[" + this.getStatusIcon() + "] " + this.label;
    }
}