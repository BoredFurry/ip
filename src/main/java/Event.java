public class Event extends Item {
    String startDate;
    String endDate;

    Event(String label) {
        super(label);
        int startDate = label.split("/from")[0].length() + 5;
        int endDate = label.split("/to")[0].length();
        this.startDate = label.substring(startDate, endDate);
        this.endDate = label.substring(endDate + 3);
    }

    @Override
    boolean checkValid() {
        return this.label.contains("/from") && this.label.contains("/to");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate
                + " to: " + this.endDate + ")";
    }
}
