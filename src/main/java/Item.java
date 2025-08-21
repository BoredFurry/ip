class Item {
    boolean status = false; // false == not done, true == done
    String label;

    Item(String label) {
        this.label = label;
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
        return this.label;
    }
}