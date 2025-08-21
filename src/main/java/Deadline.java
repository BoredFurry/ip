public class Deadline extends Item {
    String endDate;

    Deadline(String label) {
        super(label);
        String[] splitDate = label.split("/by");
        this.endDate = splitDate[1];
    }

    @Override
    boolean checkValid() {
        return label.contains("/by");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + this.endDate + ")";
    }
}
