import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {

    private BST tree;
    public Application(BST tree)
    {
        this.tree = tree;
    }
    private void Load()
    {
        File file = new File("dictionary.txt");
        try (Scanner myReader = new Scanner(file)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                this.insert(data);
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void batchLookUp()
    {
        
    }

    private void insert(String word)
    {
        this.tree.insert(tree.root, word);
    }

    private void search(String word)
    {
        this.tree.search(tree.root, word);
    }

    private void delete(String word)
    {
        this.tree.delete(word);
    }

    public void startApplication()
    {
        Scanner myReader = new Scanner(System.in);
        int operation = 0;
        while(true)
        {
            System.out.println("Enter operation number: ");
            System.out.println("1- Load");
            System.out.println("2- Print dictionary size");
            System.out.println("3- Insert a word");
            System.out.println("4- Look-up a word");
            System.out.println("5- Remove a word");
            System.out.println("6- Batch Look-ups");
            System.out.println("7- Batch Deletions");
            System.out.println("8- Exit");

            try {
                operation = myReader.nextInt();
                if(operation < 1 || operation > 8)
                {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Enter a number between 1 and 8");
                continue;
            }

            if(operation == 1)
            {
                this.Load();
            }
            else if(operation == 2)
            {
                //To do
            }
            else if(operation == 3 || operation == 4 || operation == 5)
            {
                String word = "";
                try {
                    word = myReader.nextLine();
                    char [] chars = word.toCharArray();
                    for(char c: chars)
                    {
                        if(Character.isDigit(c))
                        {
                            throw new Exception();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("invalid input. Enter a string without numbers");
                    continue;
                }
                if(operation == 3)
                {
                    this.insert(word);
                }
                else if(operation == 4)
                {
                    this.search(word);
                }
                else if(operation == 5)
                {
                    this.delete(word);
                }
                
            }
            else if(operation == 6)
            {
                
            }
            else if(operation == 7)
            {
                
            }
            else if(operation == 8)
            {
                break;
            }
            else
            {
                System.out.println("Unexpected error.");
            }
        }
        myReader.close();
    }
}
