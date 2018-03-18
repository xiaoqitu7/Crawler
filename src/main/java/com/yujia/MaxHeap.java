package com.yujia;


public class MaxHeap<T extends Comparable<? super T>> {
    private int currentSize;
    private T[] array;
    private void filterDown(int position){
        T temp=array[position];
        int child=2*position+1;
        while(child<=currentSize-1){
            if(child<currentSize-1&&array[child].compareTo(array[child+1])<0){
                child++;
            }
            if(temp.compareTo(array[child])>=0){
                break;
            }
            else{
                array[position]=array[child];
                position=child;
                child=2*child+1;
            }
        }
        array[position]=temp;
    }
    public MaxHeap(T[] items) {
        currentSize = items.length;
        array = (T[]) new Comparable[currentSize];
        int i = 0;
        for (T item : items) {
            array[i++] = item;
        }
        for (i = (currentSize - 2) / 2; i >= 0; i--) {
            filterDown(i);
        }
    }
    public T deleteTop(){
        T max=array[0];
        array[0]=array[currentSize-1];
        currentSize--;
        filterDown(0);
        return max;
    }
    public boolean isEmpty(){
        return currentSize==0;
    }
    public void filterUp(int position){
        T temp=array[position];
        int parent=(position-1)/2;
        while(position>0){
            if(array[parent].compareTo(temp)>=0){
                break;
            }
            else{
                array[position]=array[parent];
                position=parent;
                parent=(position-1)/2;
            }
            array[position]=temp;
        }
    }
    public void insert(T item) {
        enlargeArray(2*currentSize-1);
        array[currentSize] = item;
        filterUp(currentSize);
        currentSize++;
    }
    public void enlargeArray(int capacity){
        T[] oldArr=array;
        T[] newArr=(T[])new Comparable[capacity];
        for(int i=1;i<array.length;i++){
            newArr[i]=oldArr[i];
        }
        array=newArr;
    }
}
