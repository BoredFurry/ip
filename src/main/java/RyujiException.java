public class RyujiException {
    private final Functions functions = new Functions();

    // {1,"func"} = valid, {1, ""} = bad func, {0, "func"} = bad param, {0, ""} = invalid Input
    String[] parseInput(String input) {
        String[] splitCommand = input.trim().split(" ", 2);
        String checkSpace = splitCommand.length > 1 ? "1" : "0";
        String checkValid = functions.checkFunctionExists(splitCommand[0]) ? splitCommand[0] : "";
        return new String[]{checkSpace, checkValid};
    }

    boolean checkFunction(String[] parsedInput) {
        return !parsedInput[1].isEmpty();
    }

    boolean checkLength(String[] parsedInput) {
        return parsedInput[0].equals("0");
    }

    String checkInput(String input) {
        String[] parsed = this.parseInput(input);
        boolean invalidParams = this.checkLength(parsed);
        boolean invalidFn = this.checkFunction(parsed);
        if (invalidParams && invalidFn) {
            return "I don't know what you expect of me master";
        }
        if (invalidParams && !invalidFn) {
            return "You have given me the wrong format master";
        }
        return "";
    }
}
