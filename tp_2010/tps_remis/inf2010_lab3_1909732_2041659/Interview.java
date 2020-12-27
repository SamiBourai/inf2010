public class Interview<ValueType extends Comparable<? super ValueType> > {
    // 2D array of any shape which contains elements sorted from left to right, top to bottom
    private final ValueType[][] matrix;

    public Interview(ValueType[][] matrix) {
        this.matrix = matrix.clone();
    }

    /** TODO Worst case : O ( max(log n, log m) )
     *
     * Verifies if the value is contained within the 2D array
     * @param value value to verify
     * @return if value is in matrix
     */
    public boolean contains(ValueType value) {

        if(matrix.length==0)
            return false;

        int i=matrix.length - 1;
        int j=0;
        int comp=0;

        while (i >= 0 &&j <=matrix[0].length - 1) {

            comp=matrix[i][j].compareTo(value);
            if(comp==0) {
                return true;
            }
            else if (comp > 0) {
                i--;
            }
            else if (comp< 0) {
                j++;
            }

        }

        return false;
    }
}
