public class MergeSort {

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }    
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)    a[k] = aux[j++];
            else if (j > hi)     a[k] = aux[i++];
            else if (aux[i] < aux[j])    a[k] = aux[i++];
            else    a[k] = aux[j++];
        }
        assert isSorted(a, lo, hi);     
        
        // java -ea prog    enable assertions
        // java -da prog    disable assertions
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi);
            return;
        }
        if (hi <= lo)   return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (!a[mid + 1] < a[mid] )
        merge(a, aux, lo, mid, hi);
    }   
    
}


public class MergeBU {
    private static Comparable[] aux;
    private static void merge() {
        // same as above
    }

    public static void sort(Comparablep[] a) {
        int N = a.length;
        aux = new Comparable[N];
        
        // sz : sub-array size
        // loop executed lg N times
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }
}