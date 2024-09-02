public class Runner {
    public void run(String[] args) {
        //визначаємо, у якому режимі запускати програму
        if (args.length == 0) {
            startCliMode();
        }
        else if (args.length == 3){
            handleArgumentsMode(args);
        }else {
            System.out.println("Для роботи в \"Arguments mode\", потрібно передати 3 параметри: command, filepath, key");
            System.out.println("Commands: ENCRYPT, DECRYPT, BRUTE_FORCE");
            System.out.println("Приклад: java -jar myApp.jar command filePath key");
            System.out.println("Для роботи \"CLI mode\", запустіть файл без параметрів.");
            System.exit(1);
        }
    }

    private void startCliMode() {
        System.out.println("CLI mode activated.");
        CLI.Run();
    }

    private void handleArgumentsMode(String[] args) {
        System.out.println("Arguments mode activated.");
        System.out.println("Received arguments:");
        for (String arg : args) {
            System.out.println(arg);
        }
        CLI.RunArgumentMode(args);
    }
}
