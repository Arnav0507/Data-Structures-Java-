import com.sun.source.tree.Tree;
public class TreeSet<E extends Comparable<E>> {
    private TreeNode<E> root;
    private int size;
    private String output;
    private boolean hasValue;
    public TreeSet(){
        root = null;
        size = 0;
    }
    public void add(E value){
        if(root==null){
            root = new TreeNode<E>(value);
            size++;
        }
        else{
            add(root, value);
        }
    }

    public void add(TreeNode<E> node, E value){
        if(node.getValue() == value){
            return;
        }
        if(value.compareTo(node.getValue()) < 0){
            if(!node.hasLeft()){
                node.setLeft(new TreeNode<E>(value));
                size++;
            }
            else{
                add(node.getLeft(), value);
            }
        }
        if(value.compareTo(node.getValue()) > 0){
            if(!node.hasRight()){
                node.setRight(new TreeNode<E>(value));
                size++;
            }
            else{
                add(node.getRight(), value);
            }
        }
    }

    public String preOrder(){
        if(root == null){
            return "[]";
        }
        else{
            output = "[";
            preOrder(root);
            output = output.substring(0, output.length()-2);
            output += "]";
            return output;
        }
    }

    public void preOrder(TreeNode<E> node){
        if(node != null){
            output += node.getValue() + ", ";
            if(node.hasLeft()){
                preOrder(node.getLeft());
            }
            if(node.hasRight()){
                preOrder(node.getRight());
            }
        }
    }

    public String inOrder(){
        if(root == null){
            return "[]";
        }
        else{
            output = "[";
            inOrder(root);
            output = output.substring(0, output.length()-2);
            output += "]";
            return output;
        }
    }

    public void inOrder(TreeNode<E> node){
        if(node != null){
            if(node.hasLeft()){
                inOrder(node.getLeft());
            }
            output += node.getValue() + ", ";
            if(node.hasRight()){
                inOrder(node.getRight());
            }
        }
    }

    public String postOrder(){
        if(root == null){
            return "[]";
        }
        else{
            output = "[";
            postOrder(root);
            output = output.substring(0, output.length()-2);
            output += "]";
            return output;
        }
    }

    public void postOrder(TreeNode<E> node){
        if(node != null){
            if(node.hasLeft()){
                postOrder(node.getLeft());
            }
            if(node.hasRight()){
                postOrder(node.getRight());
            }
            output += node.getValue() + ", ";
        }
    }

    public void remove(E value){
        if(root == null){
            return;
        }
        if(contains(value)){
            if(!root.hasLeft() && !root.hasRight()){
                root.setValue(null);
                size = 0;
                return;
            }
            else{
                size--;
                root = remove(root, value);
            }
        }
    }

    public TreeNode<E> remove(TreeNode<E> node, E value){
        if(node == null){
            return null;
        }
        if(value.compareTo(node.getValue()) < 0){
            node.setLeft(remove(node.getLeft(), value));
        }
        else if(value.compareTo(node.getValue()) > 0){
            node.setRight(remove(node.getRight(), value));
        }
        else{
            if(!node.hasLeft() && !node.hasRight()){
                node = null;
            }
            else if(!node.hasLeft()){
                return node.getRight();
            }
            else if(!node.hasRight()){
                return node.getLeft();
            }
            else{
                E tempMinValue = minValue(node.getRight());
                node.setValue(tempMinValue);
                node.setRight(remove(node.getRight(), tempMinValue));
            }
        }
        return node;
    }

    public boolean contains(E value){
        if(root == null){
            return false;
        }
        hasValue = false;
        contains(root, value);
        return hasValue;
    }

    public void contains(TreeNode<E> node, E value){
        if(node.getValue() == value){
            hasValue = true;
        }
        if(value.compareTo(node.getValue()) < 0){
            if(!node.hasLeft()){
                hasValue = false;
            }
            else {
                contains(node.getLeft(), value);
            }
        }
        if(value.compareTo(node.getValue()) > 0){
            if(!node.hasRight()){
                hasValue = false;
            }
            else {
                contains(node.getRight(), value);
            }
        }
    }

    public E minValue(TreeNode<E> temp){
        E value;
        if(temp.hasLeft()){
            value = minValue(temp.getLeft());
        }
        else
            value = temp.getValue();
        return value;
    }

    public void rotateRight(){
        if(root == null || !root.hasLeft()){
            return;
        }
        TreeNode<E> temp = root.getLeft();
        root.setLeft(temp.getRight());
        temp.setRight(root);
        root = temp;
    }

    public void rotateLeft(){
        if(root == null || !root.hasRight()){
            return;
        }
        TreeNode<E> temp = root.getRight();
        root.setRight(temp.getLeft());
        temp.setLeft(root);
        root = temp;
    }

    public int size(){
        return size;
    }

    public class TreeNode<E extends Comparable<E>>{
        E value;
        TreeNode<E> left,right;
        public TreeNode(E value){
            this.value = value;
            left = null;
            right = null;
        }
        public E getValue(){
            return value;
        }
        public void setValue(E val){
            value = val;
        }
        public TreeNode<E> getLeft(){
            return left;
        }
        public void setLeft(TreeNode<E> left){
            this.left = left;
        }
        public TreeNode<E> getRight(){
            return right;
        }
        public void setRight(TreeNode<E> next){
            this.right = next;
        }
        public boolean hasLeft(){
            return left != null;
        }
        public boolean hasRight(){
            return right != null;
        }
        public String toString(){
            return value.toString();
        }
    }

    public static void main(String[] args) {
        TreeSet<Integer> tree = new TreeSet<Integer>();
        tree.add(10);
        tree.add(6);
        tree.add(12);
        tree.add(3);
        tree.add(7);
        tree.add(15);
        tree.add(4);
        tree.add(5);
        tree.add(10);
        tree.add(11);
        tree.add(19);
        System.out.println("PreOrder: "+tree.preOrder());
        System.out.println("InOrder: "+tree.inOrder());
        System.out.println("PostOrder: "+tree.postOrder());
        System.out.println("Size: "+tree.size());



        System.out.println("\n\nRemoving: ");
        tree.remove(10);
        System.out.println("Removed 10");
        System.out.println(tree.preOrder());
        System.out.println("Size: "+tree.size());
        tree.remove(3);
        System.out.println("\nRemoved 3");
        System.out.println(tree.preOrder());
        System.out.println("Size: "+tree.size());
        tree.remove(7);
        System.out.println("\nRemoved 7");
        System.out.println(tree.preOrder());
        System.out.println("Size: "+tree.size());
        tree.remove(15);
        System.out.println("\nRemoved 15");
        System.out.println(tree.preOrder());
        System.out.println("Size: "+tree.size());
        tree.remove(4);
        System.out.println("\nRemoved 4");
        System.out.println(tree.preOrder());
        System.out.println("Size: "+tree.size());
        tree.remove(5);
        System.out.println("\nRemoved 5");
        System.out.println(tree.preOrder());
        System.out.println("Size: "+tree.size());
        tree.remove(6);
        System.out.println("\nRemoved 6");
        System.out.println(tree.preOrder());
        System.out.println("Size: "+tree.size());
        tree.remove(12);
        System.out.println("\nRemoved 12");
        System.out.println(tree.preOrder());
        System.out.println("Size: "+tree.size());
        tree.remove(10);
        System.out.println("\nRemoved 10");
        System.out.println(tree.preOrder());
        System.out.println("Size: "+tree.size());
        tree.remove(19);
        System.out.println("\nRemoved 19");
        System.out.println(tree.preOrder());
        System.out.println("Size: "+tree.size());
        tree.remove(11);
        System.out.println("\nRemoved 11");
        System.out.println(tree.preOrder());
        System.out.println("Size: "+tree.size());

    }
}