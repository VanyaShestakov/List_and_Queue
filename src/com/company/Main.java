package com.company;

import MyList.List;
import MyQueue.Queue;
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sysScan = new Scanner(System.in);
    static List<Queue<List<Integer>>> mainList;

    public static void main(String[] args) {
        displayStartMenu();
        sysScan.close();
    }

    static void displayMenu() {
        final int AMOUNT = 7;
        final int CREATE = 1;
        final int ADD = 2;
        final int DELETE = 3;
        final int DELETE_ALL = 4;
        final int VIEW = 5;
        final int READ = 6;
        final int SAVE = 7;
        final int CLOSE = 0;
        System.out.println("""        
        ----------------------------   
                LABA 5.1          
        ----------------------------
                MAIN MENU
        ----------------------------
        1. CREATE LIST
        2. ADD ITEM
        3. DELETE ITEM
        4. DELETE ALL INCLUSIONS 
        5. VIEW LIST
        6. READ DATA FROM FILE
        7. SAVE LIST TO THE FILE
        0. CLOSE
        """);
        int choice = inputNumber(AMOUNT);
        switch (choice) {
            case CREATE:
                createList();
                break;
            case ADD:
                addItem();
                break;
            case DELETE:
                deleteItem();
                break;
            case DELETE_ALL:
                deleteAll();
                break;
            case VIEW:
                showList();
                break;
            case READ:
                readDataFromFile();
                break;
            case SAVE:
                saveDataToFile();
                break;
            case CLOSE:
                break;
        }
    }

    static void createList() {
        System.out.println("""
        -----------
        CREATE LIST
        -----------""");
        mainList = new List<>();
        System.out.println("\n*List created successfully!*");
        System.out.println("Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void addItem() {
        final int AMOUNT = 2;
        final int ADD_QUEUE = 1;
        final int ADD_LIST = 2;
        final int RETURN = 0;
        System.out.println("""
        --------
        ADD ITEM
        --------
        1. ADD QUEUE TO MAIN LIST
        2. ADD LIST TO QUEUE
        0. RETURN TO MENU""");
        int choice = inputNumber(AMOUNT);
        switch (choice) {
            case ADD_QUEUE:
                addQueue();
                break;
            case ADD_LIST:
                addList();
                break;
            case RETURN:
                displayMenu();
                break;
        }
    }

    static void deleteItem() {
        final int AMOUNT = 2;
        final int DELETE_QUEUE = 1;
        final int DELETE_LIST = 2;
        final int RETURN = 0;
        System.out.println("""
        -----------
        DELETE ITEM
        -----------
        1. DELETE QUEUE FROM MAIN LIST
        2. DELETE LIST FROM QUEUE
        0. RETURN TO MENU""");
        int choice = inputNumber(AMOUNT);
        switch (choice) {
            case DELETE_QUEUE:
                deleteQueue();
                break;
            case DELETE_LIST:
                deleteList();
                break;
            case RETURN:
                displayMenu();
                break;
        }
    }

    static void deleteAll() {
        System.out.println("""
        ---------------------
        DELETE ALL INCLUSIONS
        ---------------------""");
        System.out.println("Enter the value you want to remove:");
        int value = inputValue();
        for (int i = 0; i < mainList.size(); i++) {
            Queue<List<Integer>> tempQueue = new Queue<>();
            while (!mainList.get(i).isEmpty()) {
                List<Integer> tempList = mainList.get(i).poll();
                tempList.removeAll(value);
                tempQueue.push(tempList);
            }
            while (!tempQueue.isEmpty()) {
                mainList.get(i).push(tempQueue.poll());
            }
        }
        System.out.println("Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void showList() {
        System.out.println("""
        ---------
        MAIN LIST
        ---------""");
        System.out.println(mainList);
        System.out.println("Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void readDataFromFile() {
        System.out.println("""
        -------------------
        READ DATA FROM FILE
        -------------------""");
        System.out.println("Enter path of the file:");
        String path = inputPath();
        boolean isCorrect = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while (isCorrect && (line = reader.readLine()) != null) {
                if (line.matches("^(-?\\d+\\s?;?)+$")) {
                    addDataFromFile(line);
                } else {
                    System.out.println
                            ("Incorrect data in the file!\n" +
                                    "Example of a valid file:\n" +
                                    "num1 num2;num3 num5 num6;num7\n" +
                                    "num1 num2;num3 num5 num6;num7\n" +
                                    "...");
                    mainList.clear();
                    isCorrect = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Input error!");
        }
        String succMessage = isCorrect ? "Data read successfully!\n" : "";
        System.out.println(succMessage + "Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void saveDataToFile() {
        System.out.println("""
        -----------------
        SAVE DATA TO FILE
        -----------------""");
        System.out.println("Enter path of the file:");
        String path = inputPath();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.append(mainList.toString());
            System.out.println("*Data saved successfully!*");
        } catch (IOException e) {
            System.out.println("Output error!");
        }
        System.out.println("Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void addQueue() {
        Queue<List<Integer>> queue = new Queue<>();
        mainList.add(queue);
        System.out.println("\n*Queue added successfully!*");
        System.out.println("Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void addList() {
        if (!mainList.isEmpty()) {
            final int MAX_SIZE = 15;
            List<Integer> list = new List<>();
            System.out.println("Choose the queue:");
            showQueues();
            int index = inputNumber(mainList.size() - 1);
            System.out.println("Enter size of the list:");
            int size = inputNumber(MAX_SIZE);
            for (int i = 0; i < size; i++) {
                System.out.println("Enter value " + (i + 1) + ": ");
                int value = inputValue();
                list.add(value);
            }
            mainList.get(index).push(list);
            System.out.println("\n*List added successfully!*");
        } else {
            System.out.println("Main list is empty! Add queues to main list firstly!");
        }
        System.out.println("Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void deleteQueue() {
        if (!mainList.isEmpty()) {
            System.out.println("Choose the queue:");
            showQueues();
            int index = inputNumber(mainList.size()) - 1;
            mainList.removeAt(index);
            System.out.println("\n*Queue deleted successfully!*");

        } else {
            System.out.println("Main list is empty! Nothing to delete!");
        }
        System.out.println("Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void deleteList() {
        if (!mainList.isEmpty()) {
            System.out.println("Choose the queue:");
            showQueues();
            int index = inputNumber(mainList.size() - 1);
            if (!mainList.get(index).isEmpty()) {
                mainList.get(index).poll();
                System.out.println("\n*List deleted successfully!*");
            } else {
                System.out.println("This queue is empty! Nothing to delete!");
            }
        } else {
            System.out.println("Main list is empty! Nothing to delete!");
        }
        System.out.println("Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void showQueues(){
        for (int i = 0; i < mainList.size(); i++) {
            System.out.println((i)+ ". " + mainList.get(i).toString());
        }
    }

    static void addDataFromFile(String line) {
        String[] queues = line.split(";");
        Queue<List<Integer>> currQueue = new Queue<>();
        for (String queue: queues) {
            String[] values = queue.split(" ");
            List<Integer> currList = new List<>();
            for (String value: values) {
                currList.add(Integer.parseInt(value));
            }
            currQueue.push(currList);
        }
        mainList.add(currQueue);
    }

    static String inputPath() {
        boolean isNotExists;
        String path;
        do {
            isNotExists = false;
            path = sysScan.nextLine();
            File file = new File(path);
            if (!file.exists()) {
                System.err.println("File not found. Repeat enter!");
                isNotExists = true;
            }
        } while (isNotExists);
        return path;
    }

    static int inputValue() {
        int value = 0;
        boolean isIncorrect;
        do {
            isIncorrect = false;
            try {
                value = Integer.parseInt(sysScan.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Enter the integer number!");
                isIncorrect = true;
            }
        } while (isIncorrect);
        return value;
    }

    static int inputNumber(int range) {
        int number = 0;
        boolean isIncorrect;
        do {
            isIncorrect = false;
            try {
                number = Integer.parseInt(sysScan.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Enter the integer number!");
                isIncorrect = true;
            }
            if (!isIncorrect && (number < 0 || number > range)) {
                System.err.println("Enter the number in range [0; "+ range +"]");
                isIncorrect = true;
            }
        } while (isIncorrect);
        return number;
    }

    static void displayStartMenu() {
        final int AMOUNT = 7;
        System.out.println("""        
        ----------------------------   
                LABA 5.1          
        ----------------------------
                MAIN MENU
        ----------------------------
        1. CREATE LIST
        2. *ADD ITEM*
        3. *DELETE ITEM*
        4. *DELETE ALL INCLUSIONS* 
        5. *VIEW LIST*
        6. *READ ITEMS FROM FILE*
        7. *SAVE LIST TO THE FILE*
        0. CLOSE
        """);
        int choice = inputNumber(AMOUNT);
        if (choice == 1) {
            createList();
        } else if (choice > 1 && choice < 8) {
            System.out.println("\nThe list does not exist, create it!");
            System.out.println("Press ENTER");
            sysScan.nextLine();
            displayStartMenu();
        }
    }
}
