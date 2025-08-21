public class ToDo extends Item {

    ToDo(String label) {
        super(label);
    }

    @Override
    boolean checkValid() {
        return this.label.split(" ").length == 1;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
