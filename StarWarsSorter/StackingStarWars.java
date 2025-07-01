import java.util.Stack;
import java.io.*;
public class StackingStarWars {
    public static void run() {
        File file = new File("/Users/arnavgoel/IdeaProjects/StarWarsSorter/src/StarWarsCharacterList");
        Stack<StarWarsCharacter> stackMale = new Stack<>();
        Stack<StarWarsCharacter> stackFemale = new Stack<>();
        Stack<StarWarsCharacter> stackDroid = new Stack<>();
        Stack<StarWarsCharacter> stackYear = new Stack<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            boolean first = true;
            while((text = reader.readLine()) != null){
                //System.out.println(text);
                if(!first){
                    String[] line = text.split(",");
                    StarWarsCharacter swc = new StarWarsCharacter(line[0], line[5], line[6], line[7], line[8]);
                    if(line[6].equals("male")){
                        stackMale.push(swc);

                    }
                    if(line[6].equals("female")){
                        stackFemale.push(swc);

                    }
                    if(line[8].equals("Droid")){
                        stackDroid.push(swc);
                    }
                    if(!line[5].equals("NA")){
                        stackYear.push(swc);
                    }
                }
                first = false;
            }
            stackMale.push(new StarWarsCharacter("Name", "Birth Year", "Gender", "Home World", "Species"));
            stackFemale.push(new StarWarsCharacter("Name", "Birth Year", "Gender", "Home World", "Species"));
            stackDroid.push(new StarWarsCharacter("Name", "Birth Year", "Gender", "Home World", "Species"));
            stackYear.push(new StarWarsCharacter("Name", "Birth Year", "Gender", "Home World", "Species"));

            System.out.println("Male Characters:");
            while(!stackMale.isEmpty()) {
                System.out.println(stackMale.pop());
            }
            System.out.println("\n");

            System.out.println("Female Characters:");
            while(!stackFemale.isEmpty()){
                System.out.println(stackFemale.pop());
            }
            System.out.println("\n");

            System.out.println("Droid Characters:");
            while(!stackDroid.isEmpty()){
                System.out.println(stackDroid.pop());
            }
            System.out.println("\n");

            System.out.println("Ages:");
            while(!stackYear.isEmpty()){
                System.out.println(stackYear.pop());
            }
            System.out.println("\n");

        }
        catch(IOException e){

        }

    }

    static class StarWarsCharacter{
        String name;
        String birthYear;
        String gender;
        String homeWorld;
        String species;
        public StarWarsCharacter(String name, String birthYear, String gender, String homeWorld, String species){
            this.name = name;
            this.birthYear = birthYear;
            this.gender = gender;
            this.homeWorld = homeWorld;
            this.species = species;
        }
        public String toString(){
            //return name + "\t" + birthYear + "\t" + gender + "\t" + homeWorld + "\t" + species;
            return String.format("%-25s %-25s %-25s %-25s %-25s", name, birthYear, gender, homeWorld, species);


        }
    }

    public static void main(String[] args){
        run();
    }
}