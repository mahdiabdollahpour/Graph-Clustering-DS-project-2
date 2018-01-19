package app;

public class Sort {

    private static int partition(Edge[] a, int p, int r) {

        Edge a1 = a[p];
        Edge a2 = a[r];
        Edge a3 = a[(p + r) / 2];
        // median of three
        if (a3.cij < Math.max(a1.cij, a2.cij) && a3.cij > Math.min(a1.cij, a2.cij)) {
            Edge temp = a[(p + r) / 2];
            a[(p + r) / 2] = a[p];
            a[p] = temp;
        } else if (a2.cij < Math.max(a1.cij, a3.cij) && a2.cij > Math.min(a1.cij, a3.cij)) {
            Edge temp = a[r];
            a[r] = a[p];
            a[p] = temp;
        }

        Edge x = a[p];
        int i = p - 1;
        int j = r + 1;

        while (true) {
            //      System.out.println("i : " + i + " j : " + j);
            while (++i < r && a[i].cij < x.cij) ;
            while (--j > p && a[j].cij > x.cij) ;

            if (i < j) {
                Edge tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            } else {
                return j;
            }
        }
    }

    static int numberoftrds = 0;

    public static void quickSort(Edge[] a, int p, int r) {
        if (p < r) {
            int q = partition(a, p, r);
            if (q - p < r - q) {
                quickSort(a, p, q);

                quickSort(a, q + 1, r);

            } else {
                quickSort(a, q + 1, r);
                quickSort(a, p, q);

            }
        }
    }

    private static void bubbleSort(Edge arr[], int l, int r) {
        int n = r + 1;
        boolean sorted = false;
        for (int i = l; i < n - 1 && !sorted; i++) {
            sorted = true;
            for (int j = l; j < n - i - 1; j++)
                if (arr[j].cij > arr[j + 1].cij) {
                    // swap temp and arr[i]
                    Edge temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    sorted = false;
                }
        }
    }


    private static void insertionSort(Edge a[], int left, int right) {
        int j;
        for (int p = left; p <= right; p++) {
            Edge tmp = a[p];
            for (j = p; j > left && tmp.cij < a[j - 1].cij; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    private static void merge(Edge arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        Edge L[] = new Edge[n1];
        Edge R[] = new Edge[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].cij <= R[j].cij) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    private static void mergeSort(Edge arr[], int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void OptimumSortInsertion(Edge arr[], int low, int high, int N) {
        if (high - low < N) {
            insertionSort(arr, low, high);
            return;
        }
        if (low < high) {
            int q = partition(arr, low, high);
            if (q - low < high - q) {
                OptimumSortInsertion(arr, low, q, N);
                OptimumSortInsertion(arr, q + 1, high, N);

            } else {
                OptimumSortInsertion(arr, q + 1, high, N);
                OptimumSortInsertion(arr, low, q, N);

            }
        }
    }


    private static void OptimumSortBubble(Edge arr[], int low, int high, int N) {
        if (high - low < N) {
            bubbleSort(arr, low, high);
            return;
        }
        if (low < high) {
            int q = partition(arr, low, high);
            if (q - low < high - q) {
                OptimumSortBubble(arr, low, q - 1, N);
                OptimumSortBubble(arr, q + 1, high, N);

            } else {
                OptimumSortBubble(arr, q + 1, high, N);
                OptimumSortBubble(arr, low, q - 1, N);

            }
        }
    }

    public static void sort(Edge[] edges, int l, int r, char kindOfSort, char secondStrategy, int N) {
        switch (kindOfSort) {
            case 'q':
                quickSort(edges, l, r);

                break;
            case 'i':
                insertionSort(edges, l, r);
                break;
            case 'm':
                mergeSort(edges, l, r);
                break;
            case 'b':
                bubbleSort(edges, l, r);
                break;
            case 'o':
                switch (secondStrategy) {
                    case 'i':
                        numberoftrds = 0;
                        OptimumSortInsertion(edges, l, r, N);
                        break;
                    case 'b':
                        numberoftrds = 0;
                        System.out.println("its optimum bubble");
                        OptimumSortBubble(edges, l, r, N);
                        break;
                }
                break;
        }
    }

    public static void sort(Edge[] edges, int l, int r, char kindOfSort) {
        sort(edges, l, r, kindOfSort, 'n', 0);
    }


}
