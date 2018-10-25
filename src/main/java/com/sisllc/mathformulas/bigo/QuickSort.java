/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.sisllc.mathformulas.bigo;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/**
 * The Class QuickSort.
 *
 * @param <T> the generic type
 */
public class QuickSort<T extends Comparable<T>> {

    /**
     * Sort.
     *
     * @param array the array
     */
    public void sort(T[] array) {
        array = quicksort(array, 0, array.length - 1);
    }

    /**
     * Quicksort.
     *
     * @param array the array
     * @param lo the lo
     * @param hi the hi
     * @return the t[]
     */
    private T[] quicksort(T[] array, int lo, int hi) {
        if (hi > lo) {
            int partitionPivotIndex = (int) (Math.random() * (hi - lo) + lo);
            int newPivotIndex = partition(array, lo, hi, partitionPivotIndex);
            quicksort(array, lo, newPivotIndex - 1);
            quicksort(array, newPivotIndex + 1, hi);
        }
        return (T[]) array;
    }

    /**
     * Partition.
     *
     * @param array the array
     * @param lo the lo
     * @param hi the hi
     * @param pivotIndex the pivot index
     * @return the int
     */
    private int partition(T[] array, int lo, int hi, int pivotIndex) {
        T pivotValue = array[pivotIndex];
        swap(array, pivotIndex, hi); // send to the back
        int index = lo;
        for (int i = lo; i < hi; i++) {
            if ((array[i]).compareTo(pivotValue) <= 0) {
                swap(array, i, index);
                index++;
            }
        }
        swap(array, hi, index);
        return index;
    }

    /**
     * Swap.
     *
     * @param array the array
     * @param i the i
     * @param j the j
     */
    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
