package com.Jhatkaa;

/**
 * Created by Vikram Aditya on 2/12/2017.
 */
public class MinHeap {
    private int[] Heap;
    private int maxsize;
    private int size;

    public MinHeap(int max) {
        maxsize = max;
        Heap = new int[maxsize];
        size = 0;
    }

    private int getRoot() {
        if (size == 0) {
            return -1;
        }
        return Heap[0];
    }

    private int leftchild(int pos) {
        return 2 * pos;
    }

    private int rightchild(int pos) {
        return 2 * pos + 1;
    }

    private int getParent(int pos) {
        return (pos) / 2;
    }

    private boolean isleaf(int pos) {
        return ((pos > size / 2) && (pos <= size));
    }

    private void swap(int pos1, int pos2) {
        int tmp;

        tmp = Heap[pos1];
        Heap[pos1] = Heap[pos2];
        Heap[pos2] = tmp;
    }

    public void insert(int elem) {
        Heap[size] = elem;
        int current = size;
        int parent = getParent(current);
        while (current > 0 && Heap[current] < Heap[parent]) {
            swap(current, parent);
            current = getParent(current);
        }
        size++;
    }

    public int removemin() {
        size--;
        int minElement = Heap[size];
        swap(0, size);
        if (size > 0)
            pushdown(0);
        return minElement;
    }

    private void pushdown(int position) {
        int smallestchild;
        while (!isleaf(position)) {
            smallestchild = leftchild(position);
            if ((smallestchild < size) && (Heap[smallestchild] > Heap[smallestchild + 1]))
                smallestchild = smallestchild + 1;
            if (Heap[position] <= Heap[smallestchild]) return;
            swap(position, smallestchild);
            position = smallestchild;
        }
    }
}
