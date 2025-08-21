public class ToDo extends Item{

    ToDo(String label) {
        super(label);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
