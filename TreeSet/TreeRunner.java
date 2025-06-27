import java.util.ArrayList;
import java.util.Random;

public class TreeRunner {
    public static void main(String[] args){
        TreeSet<Integer> tree = new TreeSet<>();
        //Insert 30 distinct random numbers from 1-100 into tree
        while(tree.size() < 30){
            int num = (int)(Math.random()*100)+1;
            tree.add(num);
            System.out.print(num + " ");
        }
        //tree size
        System.out.println("\nTree Size: " + tree.size());
        System.out.println("\nOriginal Tree: ");
        System.out.println(tree.preOrder());
        System.out.println(tree.inOrder());
        System.out.println(tree.postOrder());

        //Instantiate a new TreeSet and copy all the values from the original tree into the new tree using a PreOrder traversal
        String s = tree.preOrder();
       // System.out.println(s);
        s = s.substring(1, s.length()-1);
        String[] list = s.split(",");
        TreeSet<Integer> tree2 = new TreeSet<>();
        for (String value : list) {
            tree2.add(Integer.valueOf(value.replaceAll(" ", "")));
        }
        //Output all the values from the new tree using PreOrder, InOrder, and PostOrder traversals to
        //verify that it has made an exact duplicate of your original tree.
        System.out.println("\nDuplicated TreeSet by Preorder: ");
        System.out.println(tree2.preOrder());
        System.out.println(tree2.inOrder());
        System.out.println(tree2.postOrder());

        //Same thing but InOrder
        s = tree.inOrder();
        // System.out.println(s);
        s = s.substring(1, s.length()-1);
        list = s.split(",");
        TreeSet<Integer> tree3 = new TreeSet<>();
        for (String value : list) {
            tree3.add(Integer.valueOf(value.replaceAll(" ", "")));
        }
        //Output all the values from the new tree using PreOrder, InOrder, and PostOrder traversals to
        //verify that it has made an exact duplicate of your original tree.
        System.out.println("\nDuplicated TreeSet by InOrder: ");
        System.out.println(tree3.preOrder());
        System.out.println(tree3.inOrder());
        System.out.println(tree3.postOrder());
        System.out.println("The interesting thing about this transformation is that the post order is now in descending order");

        //Same thing but PostOrder
        s = tree.postOrder();
        // System.out.println(s);
        s = s.substring(1, s.length()-1);
        list = s.split(",");
        TreeSet<Integer> tree4 = new TreeSet<>();
        for (String value : list) {
            tree4.add(Integer.valueOf(value.replaceAll(" ", "")));
        }
        //Output all the values from the new tree using PreOrder, InOrder, and PostOrder traversals to
        //verify that it has made an exact duplicate of your original tree.
        System.out.println("\nDuplicated TreeSet by PostOrder: ");
        System.out.println(tree4.preOrder());
        System.out.println(tree4.inOrder());
        System.out.println(tree4.postOrder());
        System.out.println("The interesting thing about this transformation is that the post order goes up until the maximum and then declines like a bell curve");

        TreeSet<String> stringTreeSet = new TreeSet<>();
        System.out.println();
        while(stringTreeSet.size() < 20){
            String strTemp = stringGenerator(3);
            stringTreeSet.add(strTemp);
            System.out.print(strTemp + " ");
        }
        System.out.println("\nString Tree: ");
        System.out.println(stringTreeSet.preOrder());
        System.out.println(stringTreeSet.inOrder());
        System.out.println(stringTreeSet.postOrder());

        for(int i = 1; i <= 3; i++){
            stringTreeSet.rotateRight();
            System.out.println("\nRight Rotation: " + i);
            System.out.println(stringTreeSet.preOrder());
            System.out.println(stringTreeSet.inOrder());
            System.out.println(stringTreeSet.postOrder());
        }

        for(int i = 1; i <= 3; i++){
            stringTreeSet.rotateLeft();
            System.out.println("\nLeft Rotation: " + i);
            System.out.println(stringTreeSet.preOrder());
            System.out.println(stringTreeSet.inOrder());
            System.out.println(stringTreeSet.postOrder());
        }

        TreeSet<Integer> tree5 = new TreeSet<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        //Insert 10 distinct random numbers from 1-100 into tree
        System.out.println();
        while(tree5.size() < 10){
            int num = (int)(Math.random()*100)+1;
            tree5.add(num);
            arrayList.add(num);
            System.out.print(num + " ");
        }

        while(arrayList.size() > 0){
            int tempIndex = (int)(Math.random()*arrayList.size());
            int num = arrayList.remove(tempIndex);
            System.out.println("\nRemoving: " + num);
            tree5.remove(num);
            System.out.println(tree5.preOrder());
            System.out.println(tree5.inOrder());
            System.out.println(tree5.postOrder());
        }
    }

    static String stringGenerator(int length){
        Random r = new Random();
        String s = "";
        for(int i = 0; i < length; i++){
            char c = (char)(r.nextInt(26) + 'a');
            s+=c;
        }
        return s;
    }
}
