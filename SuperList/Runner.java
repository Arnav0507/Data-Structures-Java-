public class Runner {
    public Runner(){
        //ArrayList (30 random integers)
        SuperList<Integer> superArrayList = new SuperList<>();
        for(int i = 0; i < 30; i++){
            superArrayList.add((int)(Math.random()*1000+1));
        }
        System.out.println("Original ArrayList: " + superArrayList);
        System.out.println("Size: " + superArrayList.size());

        //ArrayList to Stack (using push, remove)
        SuperList<Integer> superStackList = new SuperList<>();
        while(!superArrayList.isEmpty()){
            superStackList.push(superArrayList.remove(0));
        }
        System.out.println("As a Stack: " + superStackList);

        //Stack to Queue (using pop, add)
        SuperList<Integer> superQueueList = new SuperList<>();
        while(!superStackList.isEmpty()){
            superQueueList.add(superStackList.pop());
        }
        System.out.println("As a Queue: " + superQueueList);

        //Queue back to ArrayList in random indices (using poll, add by index)***
        for(int i = 0; i < superQueueList.size(); i++){
            superArrayList.add(-1);
        }
        while(!superQueueList.isEmpty()){
            int randomIndex = (int)(Math.random()* (superQueueList.size()));
            superArrayList.add(randomIndex, superQueueList.poll());

            for(int i = 0; i < superArrayList.size(); i++){
                if(superArrayList.get(i) == -1){
                    superArrayList.remove(i);
                    break;
                }
            }
        }
        System.out.println("As a random ArrayList: " + superArrayList);

        //sum(normal, even index, odd index), must use get
        int sum = 0;
        for(int i = 0; i < superArrayList.size(); i++){
            sum+=superArrayList.get(i);
        }
        System.out.println("Sum: " + sum);

        int sumEven = 0;
        for(int i = 0; i < superArrayList.size(); i+=2){
            sumEven+= superArrayList.get(i);
        }
        System.out.println("Sum of even indices: " + sumEven);

        int sumOdd = 0;
        for(int i = 1; i < superArrayList.size(); i+=2){
            sumOdd+= superArrayList.get(i);
        }
        System.out.println("Sum of odd indices: " + sumOdd);

        //Create duplicates of all even values, must use get
        int originalSize = superArrayList.size();
        for(int i = 0; i < originalSize; i++){
            if(superArrayList.get(i) % 2 == 0){
                superArrayList.add(superArrayList.get(i));
            }
        }
        System.out.println("After duplicating evens: " + superArrayList);

        //Remove all instances of values that are divisible by 3, must use get & remove
        for(int i = 0; i < superArrayList.size(); i++){
            if(superArrayList.get(i) % 3 == 0){
                superArrayList.remove(i);
                i--;
            }
        }
        System.out.println("After removing multiples of 3: " + superArrayList);

        //Insert the value 55555 into the 4th position, use add by index
        superArrayList.add(3,55555);
        System.out.println("After adding 55555: " + superArrayList);

        //Sort in descending order, must use get & remove
        int currSize = superArrayList.size();
        for(int i = 0; i < superArrayList.size(); i++){
            int max = 0;
            int maxIndex = 0;
            for(int j = 0; j < currSize; j++){
                if(superArrayList.get(j) > max){
                    max = superArrayList.get(j);
                    maxIndex = j;
                }
            }
            currSize--;
            superArrayList.remove(maxIndex);
            superArrayList.add(max);
        }
        System.out.println("Descending: " + superArrayList);

        //Median and split in half
        double median = 0.0;
        SuperList<Integer> beforeMedian = new SuperList<>();
        SuperList<Integer> afterMedian = new SuperList<>();
        if(superArrayList.size() % 2 == 1){
            median = superArrayList.get(superArrayList.size()/2)*1.0;
            for(int i = 0; i < superArrayList.size()/2; i++){
                beforeMedian.add(superArrayList.get(i));
            }
            for(int i = superArrayList.size()/2+1; i < superArrayList.size(); i++){
                afterMedian.add(superArrayList.get(i));
            }
        }
        else{
            median = ((superArrayList.get((superArrayList.size()/2) - 1))+(superArrayList.get(superArrayList.size()/2)))/2.0;
            for(int i = 0; i < superArrayList.size()/2; i++){
                beforeMedian.add(superArrayList.get(i));
            }
            for(int i = superArrayList.size()/2; i < superArrayList.size(); i++){
                afterMedian.add(superArrayList.get(i));
            }
        }

        System.out.println("Median: " + median);
        System.out.println("Size: " + superArrayList.size());
        System.out.println("Before the median: " + beforeMedian);
        System.out.println("After the median: " + afterMedian);
        System.out.println("\n");

        //SuperList with Strings!!
        SuperList<String> superStringList = new SuperList<>();
        String sentence = "I love eating toasted cheese and tuna sandwiches";
        String[] words = sentence.split(" ");
        for(int i = 0; i < words.length; i++){
            superStringList.add(words[i]);
        }
        System.out.println("Word list: " + superStringList);

        for(int i = 0; i < superStringList.size(); i++){
            if(superStringList.get(i).length() <= 3){
                superStringList.remove(i);
                i--;
            }
        }
        System.out.println("Word list after removing short words: " + superStringList);
        for (int j = 1; j < superStringList.size(); j++) {
            String key = superStringList.get(j);
            int i = j-1;
            while ( (i > -1) && ( superStringList.get(i).length() > key.length() ) ) {
                superStringList.set(i+1, superStringList.get(i));
                i--;
            }
            superStringList.set(i+1, key);
        }
        System.out.println("Ascending word list: " + superStringList);
        double lengthSum = 0.0;
        for(int i = 0; i < superStringList.size(); i++){
            lengthSum += superStringList.get(i).length();
        }
        double avgLength = lengthSum/superStringList.size();
        System.out.println("Average word length: " + avgLength);



    }

    public static void main(String args[]) {
        Runner runner = new Runner();
    }
}
