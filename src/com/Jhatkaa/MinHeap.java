package com.Jhatkaa;

/**
 * Created by Vikram Aditya on 2/12/2017.
 */
public class MinHeap {
    private int[] Heap;
    private int maxsize;
    private int pointer;

    public MinHeap(int max) {
        maxsize = max;
        Heap = new int[maxsize];
        pointer = 0;
    }

    public int getRoot() {
        if (pointer == 0) {
            return -1;
        }
        return Heap[0];
    }

    private int leftchild(int pos) {
        return 2 * pos + 1;
    }

    private int rightchild(int pos) {
        return 2 * (pos + 1);
    }

    private int getParent(int pos) {
        return (pos-1) / 2;
    }

    private boolean isleaf(int pos) {
        return ((pos >= pointer / 2) && (pos < pointer));
    }

    private void swap(int pos1, int pos2) {
        int tmp;

        tmp = Heap[pos1];
        Heap[pos1] = Heap[pos2];
        Heap[pos2] = tmp;
    }

    public void insert(int elem) {
        Heap[pointer] = elem;
        int current = pointer;
        int parent = getParent(current);
        while (current > 0 && Heap[current] < Heap[parent]) {
            swap(current, parent);
            current = parent;
            parent = getParent(current);
        }
        pointer++;
    }

    public int removemin() {
        int minElement = getRoot();
        pointer--;
        swap(0, pointer);
        if (pointer > 1)
            pushdown(0);
        return minElement;
    }

    private void pushdown(int position) {
        int smallestchild;
        while (position < pointer && !isleaf(position)) {
            smallestchild = leftchild(position);
            if ((smallestchild < pointer -1) && (Heap[smallestchild] > Heap[smallestchild + 1]))
                smallestchild = smallestchild + 1;
            if (smallestchild < pointer && Heap[position] <= Heap[smallestchild]) return;
            swap(position, smallestchild);
            position = smallestchild;
        }
    }
}
