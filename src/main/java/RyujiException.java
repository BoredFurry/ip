public class RyujiException { ;
    Functions functions = new Functions();


    // {1,"func"} = valid, {1, ""} = bad func, {0, "func"} = bad param, {0, ""} = invalid Input
    String[] parseInput(String input) { // {check > 1 space, check if valid function}
        String checkSpace = "0";
        String checkValid = "";
        String[] splitCommand = input.split(" ");
        if (splitCommand.length > 2) { // checks if there is a " " to denote params
            checkSpace = "1";
        }
        if (functions.checkFunctionExists(splitCommand[0])) { // check if valid function
            checkValid = splitCommand[1];
        }
        return new String[]{checkSpace, checkValid};
    }

    boolean checkFunction(String[] parsedInput) {
        return !parsedInput[1].isEmpty();
    }

    boolean checkLength(String[] parsedInput) {
        return parsedInput[0].equals("0");
    }

    String checkInput(String input) {
        boolean invalidParams = this.checkLength(this.parseInput(input));
        boolean invalidFn = this.checkFunction(this.parseInput(input));
        if (invalidParams && invalidFn) {
            return "I don't know what you expect of me master";
        }
        if (invalidParams && !invalidFn) {
            return "You have given me the wrong format master";
        }
        return "";
    }
}
