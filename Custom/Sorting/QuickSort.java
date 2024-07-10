
class QuickSort {

    final int CUTOFF = 10;
    
    int partition(Comparable a[], int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi)
                    break;
            }
            while (less(a[lo], a[--j]));

            if (i >= j) break;

            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }

    private boolean less(Comparable _this, Comparable _that) {
        return _this.compareTo(_that) < 0;
    }

    private void exchange(Comparable[] a, int _this, int _that) {
        Comparable temp = a[_this];
        a[_this] = a[_that];
        a[_that] = temp;
    }

    void sort(Comparable a[]) {
        // StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    } 
    
    void sort(Comparable a[], int lo, int hi) {
        // if (hi <= lo + CUTOFF + 1) {
        //     Insertion.sort(a, lo, hi);
        //     return;
        // }
        if (hi <= lo) 
            return;
        // int m = medianOf3(a, lo, lo + (hi - lo) / 2, hi);
        // swap(a, lo, m);
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }
}



// public static void quickSort(int[] array, int lo, int hi) {
//     int i = lo;
//     int j = hi;
//     int pivot = array[(lo + hi) / 2];   // pivot = middle element

//     do {
//         while ((array[i] < pivot) && (i < hi))
//             i++;
//         while ((array[j] > pivot) && (j > lo))
//             j--;
        
//         if (i <= j) {
//             int temp = array[i];
//             array[i] = array[j];
//             array[j] = temp;
//             i++;
//             j--;
//         }
//     } while (i <= j);

//     if (lo < j) quickSort(array, lo, j);
//     if (i < hi) quickSort(array, i, hi);
// }