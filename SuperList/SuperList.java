import java.util.EmptyStackException;
public class SuperList<E>{
    private ListNode<E> root;
    private ListNode<E> end;
    private int size;
    public SuperList(){
        root = null;
        end = null;
        size = 0;
    }
    public void add(E value){
        ListNode<E> temp = new ListNode<E>(value);
        if(root==null){
            root = temp;
            end = root;
        }
        else{
            end.setNext(temp);
            temp.setPrevious(end);
            end = temp;
        }
        size++;

    }
    public void add(int index, E value){
        ListNode<E> node = new ListNode<E>(value);

        if(index<0 || index>size)
        {
            throw new ArrayIndexOutOfBoundsException();
        }

        if(index == size){ //adding to an empty list or add to the end
            add(value);
        }
        else if(index == 0){ //add to front
            root.setPrevious(node);
            node.setNext(root);
            root = node;
            size++;
        }

        //add to the middle
        else{
            ListNode<E> tempNext = root;

            for(int i = 0; i < index; i++){
                tempNext = tempNext.getNext();
            }
            ListNode<E> tempPrev = tempNext.getPrevious();
            tempPrev.setNext(node);
            tempNext.setPrevious(node);
            node.setNext(tempNext);
            node.setPrevious(tempPrev);
            size++;
        }


    }
    public E remove(int index){
        if(index<0 || index>=size)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        if(index == size-1){ //remove last or remove the last value
            return pop();
        }
        if(index == 0){ //remove front
            return poll();
        }

        //remove from the middle

        ListNode<E> temp = root;

        for(int i = 0; i < index; i++){
            temp = temp.getNext();
        }

        ListNode<E> next=temp.getNext();
        ListNode<E> prev=temp.getPrevious();
        next.setPrevious(prev);
        prev.setNext(next);
        size--;
        return temp.getValue();
    }

    public E pop(){
        if(size == 0){
            throw new EmptyStackException();
        }
        if(size == 1){
            E val = end.getValue();
            clear();
            return val;

        }
        E val = end.getValue();
        end = end.getPrevious();
        end.setNext(null);
        size--;
        return val;

    }

    public E poll(){
        if(size == 0){
            return null;
        }
        if(size == 1){
            E val = root.getValue();
            clear();
            return val;
        }

        E val = root.getValue();
        root = root.getNext();
        root.setPrevious(null);
        size--;
        return val;

    }

    public E queuePeek(){
        E val = root.getValue();
        return val;
    }

    public E stackPeek(){
        E val = end.getValue();
        return val;
    }


    public void clear(){
        root = null;
        end = null;
        size = 0;
    }

    public void push(E value){
        add(value);
    }
    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }
    public E get(int index){


        if(index<0 || index>=size)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        if(index == 0){
            return root.getValue();
        }
        if(index == size-1){
            return end.getValue();
        }
        //add to the middle

        ListNode<E> temp = root;
        for(int i = 0; i < index; i++){
            temp = temp.getNext();
        }

        return temp.getValue();
    }

    public void set(int index, E value){
        ListNode<E> temp = root;

        if(index<0 || index>=size || size == 0)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        else if(index == 0){
            root.setValue(value);
        }
        else if(index == size-1){
            end.setValue(value);
        }
        //add to the middle
        else{
            for(int i = 0; i < index; i++){
                temp = temp.getNext();
            }
            temp.setValue(value);
        }

    }

    public boolean contains(E value){
        ListNode<E> temp = root;

        if(size == 0)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        //add to the middle
        else{
            for(int i = 0; i < size; i++){
                temp = temp.getNext();
                if(temp.getValue() == value){
                    return true;
                }
            }

        }
        return false;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString(){
        ListNode<E> temp = root;
        String s = "[";
        for(int i = 0; i < size; i++){
            s+=temp.getValue();
            s+=", ";
            temp = temp.getNext();
        }
        if(s.contains(", "))
            s=s.substring(0,s.length()-2);
        s+="]";
        return s;
    }

    public class ListNode<E>{
        E value;
        ListNode<E> next,previous;
        public ListNode(E value){
            this.value = value;
        }
        public E getValue(){
            return value;
        }
        public void setValue(E val){
            value = val;
        }
        public ListNode<E> getPrevious(){
            return previous;

        }
        public void setPrevious(ListNode<E> previous){
            this.previous = previous;
        }
        public ListNode<E> getNext(){
            return next;
        }
        public void setNext(ListNode<E> next){
            this.next = next;
        }
        public boolean hasPrevious(){
            if(previous==null){
                return false;
            }
            return true;
        }
        public boolean hasNext(){
            if(next==null){
                return false;
            }
            return true;
        }


    }

    public static void main(String args[]){

        SuperList<Integer> list=new SuperList<Integer>();

        //This runner will test push(value), add(value), pop(), and poll()

        list.push(10);
        list.add(2);
        list.push(5);
        list.add(12);
        list.set(0,44);
        System.out.println(list);

        //list should have stored values as: {10, 2, 5, 12}

        System.out.println(list.pop());		//should display 12
        System.out.println(list.poll());	//should display 10
        System.out.println(list.pop());		//should display 5
        System.out.println(list.poll());	//should display 2
        System.out.println(list.poll());	//should display null
        //System.out.println(list.pop());		//should throw an EmptyStackException

        list.add(0);
        list.add(0,10);
        System.out.println(list+" "+list.size());
        list.add(list.size(),12);
        list.add(1,7);

        //list should have stored values as: {10, 7, 0, 12}
        for(int x=0;x<list.size();x++)
        {
            System.out.println(list.get(x));
        }

        System.out.println();
        System.out.println(list.remove(0));				//should display 10
        System.out.println(list.remove(list.size()-1));	//should display 12
        System.out.println(list.remove(1));				//should display 0
        System.out.println(list.remove(0));				//should display 7
        System.out.println(list.remove(10));				//should throw IndexOutOfBoundsException or ArrayIndexOutOfBoundsException


    }
}