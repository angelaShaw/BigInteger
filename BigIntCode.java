package bigint;

import java.util.function.BinaryOperator;

/**
 * This class encapsulates a BigInteger, i.e. a positive or negative integer with
 * any number of digits, which overcomes the computer storage length limitation of
 * an integer.
 *
 */
public class BigInteger {

    /**
     * True if this is a negative integer
     */
    boolean negative;

    /**
     * Number of digits in this integer
     */
    int numDigits;

    /**
     * Reference to the first node of this integer's linked list representation
     * NOTE: The linked list stores the Least Significant Digit in the FIRST node.
     * For instance, the integer 235 would be stored as:
     * 5 --> 3  --> 2
     * <p>
     * Insignificant digits are not stored. So the integer 00235 will be stored as:
     * 5 --> 3 --> 2  (No zeros after the last 2)
     */
    DigitNode front;

    /**
     * Initializes this integer to a positive number with zero digits, in other
     * words this is the 0 (zero) valued integer.
     */
    public BigInteger() {
        negative = false;
        numDigits = 0;
        front = null;
    }

    /**
     * Parses an input integer string into a corresponding BigInteger instance.
     * A correctly formatted integer would have an optional sign as the first
     * character (no sign means positive), and at least one digit character
     * (including zero).
     * Examples of correct format, with corresponding values
     * Format     Value
     * +0            0
     * -0            0
     * +123        123
     * 1023       1023
     * 0012         12
     * 0             0
     * -123       -123
     * -001         -1
     * +000          0
     * <p>
     * Leading and trailing spaces are ignored. So "  +123  " will still parse
     * correctly, as +123, after ignoring leading and trailing spaces in the input
     * string.
     * <p>
     * Spaces between digits are not ignored. So "12  345" will not parse as
     * an integer - the input is incorrectly formatted.
     * <p>
     * An integer with value 0 will correspond to a null (empty) list - see the BigInteger
     * constructor
     *
     * @param integer Integer string that is to be parsed
     * @return BigInteger instance that stores the input integer.
     * @throws IllegalArgumentException If input is incorrectly formatted
     */
    public static BigInteger parse(String integer)
            throws IllegalArgumentException {

        BigInteger userint = new BigInteger(); //big integer obj w/ val
        String temp = new String(integer); //temp with integer values

        //////////////////////////////////////////////////////////////////
        if (temp.length() == 0) { //checks if nothing ios inputed
            DigitNode n = new DigitNode(0, null);
            userint.front = n;
            userint.negative = false;
            userint.numDigits = 1;
            return userint;
        }

        char firstVal = temp.charAt(0);

        //checking first value of string is useless
        if (temp.length() == 1 && (firstVal == '+' || firstVal == '-')) {
            DigitNode n = new DigitNode(0, null);
            userint.front = n;
            userint.negative = false;
            userint.numDigits = 1;
            return userint;
        }

        ///////////////////////////////////////////////////////////////////////////
        //removes excess  spaces try trim
        while (temp.length() >= 1 && firstVal == ' ') {

            if (temp.length() == 1) { //only char left is useless
                DigitNode n = new DigitNode(0, null);
                userint.front = n;
                userint.negative = false;
                userint.numDigits = 1;
                return userint;
            }
            temp = temp.substring(1, temp.length());
            firstVal = temp.charAt(0);

        }

        char lv = temp.charAt(temp.length() - 1);
        while (temp.length() >= 1 && lv == ' ') {

            if (temp.length() == 1) { //only char left is useless
                DigitNode n = new DigitNode(0, null);
                userint.front = n;
                userint.negative = false;
                userint.numDigits = 1;
                return userint;
            }
            temp = temp.substring(0, temp.length() - 1);
            lv = temp.charAt(temp.length() - 1);

        }

        //////////////////////////////////////////////////////////////////////////

        //checking for signs
        if (firstVal == '-') {
            userint.negative = true;
            temp = temp.substring(1, temp.length());
            firstVal = temp.charAt(0);
        } else if (firstVal == '+') {
            userint.negative = false;
            temp = temp.substring(1, temp.length());
            firstVal = temp.charAt(0);
        }
        //*****Only numbers after this point no more characters

        ///////////////////////////////////////////////////////////////////////////


        //removes excess 0's
        while (temp.length() >= 1 && firstVal == '0') {

            if (temp.length() == 1) { //only char left is useless
                DigitNode n = new DigitNode(0, null);
                userint.front = n;
                userint.negative = false;
                userint.numDigits = 1;
                return userint;
            }
            temp = temp.substring(1, temp.length());
            firstVal = temp.charAt(0);

        }
        ///////////////////////////////////////////////////////////////////////////

        char lastVal = temp.charAt(temp.length() - 1);

        //store values
        while (temp.length() >= 1) {
            if (Character.isDigit(lastVal)) {

                DigitNode tempNode = userint.front;
                if (tempNode == null) {
                    DigitNode n = new DigitNode(Character.getNumericValue(lastVal), null);
                    userint.numDigits++;
                    userint.front = n;
                } else {
                    DigitNode n = new DigitNode(Character.getNumericValue(lastVal), null);
                    //tempNode = tempNode.next;
                    while (tempNode.next != null) {
                        tempNode = tempNode.next;
                    }
                    tempNode.next = n;
                    userint.numDigits++;

                }

            } else {
                throw new IllegalArgumentException();
            }

            //no more left
            if (temp.length() == 1)
                break;

            temp = temp.substring(0, temp.length() - 1);
            lastVal = temp.charAt(temp.length() - 1);

        }


        return userint;
    }

    /**
     * Adds the first and second big integers, and returns the result in a NEW BigInteger object.
     * DOES NOT MODIFY the input big integers.
     * <p>
     * NOTE that either or both of the input big integers could be negative.
     * (Which means this method can effectively subtract as well.)
     *
     * @param first  First big integer
     * @param second Second big integer
     * @return Result big integer
     */
    public static BigInteger add(BigInteger first, BigInteger second) {

        /* IMPLEMENT THIS METHOD */
        BigInteger useri = new BigInteger(); //new bigint

        /////////////////////////////////////////AD
        if (first.negative == second.negative) {
            int carry = 0; //holds extra
            int total = 0; //holds total
            int loops = second.numDigits; //number of loops


            if (first.numDigits < second.numDigits) { //check to c which is smaller
                loops = first.numDigits;
            }

            DigitNode temp1 = first.front; //temp pointer
            DigitNode temp2 = second.front; //temp pointer


            for (int i = 0; i < loops; i++) {

                if (useri.front == null) { //if nothing is there
                    total = temp1.digit + temp2.digit;
                    if (total <= 9) { //if t less than or equal to 9
                        DigitNode n = new DigitNode(total, null);
                        useri.front = n;
                        useri.numDigits++;
                    } else { //if 10 or greater
                        int ones = total % 10; //get ones digit
                        DigitNode n = new DigitNode(ones, null);
                        useri.front = n;
                        useri.numDigits++;
                        carry = total / 10;
                    }
                } else {
                    total = temp1.digit + temp2.digit + carry;
                    carry = 0;
                    if (total <= 9) {
                        DigitNode n = new DigitNode(total, null);
                        DigitNode temp = useri.front;
                        while (temp.next != null)
                            temp = temp.next;
                        temp.next = n;
                        useri.numDigits++;
                    } else {
                        int ones = total % 10;
                        DigitNode n = new DigitNode(ones, null);
                        carry = total / 10;
                        DigitNode temp = useri.front;
                        while (temp.next != null)
                            temp = temp.next;
                        temp.next = n;
                        useri.numDigits++;
                    }
                }

                temp1 = temp1.next;
                temp2 = temp2.next;

            }

            while (carry != 0 || temp1 != null || temp2 != null) {
                while (temp1 != null) {
                    total = carry + temp1.digit;
                    carry = 0;
                    if (total <= 9) {
                        DigitNode n = new DigitNode(total, null);
                        DigitNode temp = useri.front;
                        while (temp.next != null)
                            temp = temp.next;
                        temp.next = n;
                        useri.numDigits++;
                    } else {
                        int ones = total % 10;
                        DigitNode n = new DigitNode(ones, null);
                        carry = total / 10;
                        DigitNode temp = useri.front;
                        while (temp.next != null)
                            temp = temp.next;
                        temp.next = n;
                        useri.numDigits++;
                    }
                    temp1 = temp1.next;

                }
                while (temp2 != null) {
                    total = carry + temp2.digit;
                    carry = 0;
                    if (total <= 9) {
                        DigitNode n = new DigitNode(total, null);
                        DigitNode temp = useri.front;
                        while (temp.next != null)
                            temp = temp.next;
                        temp.next = n;
                        useri.numDigits++;
                    } else {
                        int ones = total % 10;
                        DigitNode n = new DigitNode(ones, null);
                        carry = total / 10;
                        DigitNode temp = useri.front;
                        while (temp.next != null)
                            temp = temp.next;
                        temp.next = n;
                        useri.numDigits++;
                    }
                    temp2 = temp2.next;
                }
                while (temp1 == null && temp2 == null && carry > 0) {
                    total = carry;
                    carry = 0;
                    DigitNode n = new DigitNode(total, null);
                    DigitNode temp = useri.front;
                    while (temp.next != null)
                        temp = temp.next;
                    temp.next = n;
                    useri.numDigits++;
                }

            }
            useri.negative = first.negative;
        }
        else{ //subtract
            if(firstIsBigger(first,second)){
                useri = substractionHelper(first, second);
            }
            else
                useri = substractionHelper(second, first);

        }

        return useri;
    }
        // following line is a placeholder for compilation


        /**
         * Returns the BigInteger obtained by multiplying the first big integer
         * with the second big integer
         *
         * This method DOES NOT MODIFY either of the input big integers
         *
         * @param first First big integer
         * @param second Second big integer
         * @return A new BigInteger which is the product of the first and second big integers
         */
        public static BigInteger multiply (BigInteger first, BigInteger second){

            /* IMPLEMENT THIS METHOD */

            BigInteger userint = new BigInteger();
            BigInteger userintfinal = new BigInteger();
            int loops = first.numDigits;
            int carry = 0;
            DigitNode temp1 = first.front;
            int zCounter = 0;
            while(temp1 != null) {

                userint = multiplyHelper(second, temp1, zCounter);
                if(userintfinal.front == null){
                    userintfinal = copy(userint);
                    userintfinal.numDigits = userint.numDigits;
                    userint.front = null;
                    userint.numDigits = 0;
                }
                else{
                    userintfinal = add(userint, userintfinal);
                    userint.front = null;
                    userint.numDigits = 0;

                }
                temp1 = temp1.next;
                zCounter++;
            }

            if(first.negative == second.negative){
                userintfinal.negative = false;

            }
            else{
                userintfinal.negative = true;
            }
//            //check for zeros
//            DigitNode t = userintfinal.front;
//            while (t != null) {
//                DigitNode n = userintfinal.front;
//                DigitNode prev = userintfinal.front;
//                while (n.next != null) {
//                    prev = n;
//                    n = n.next;
//                }
//                if (n.digit == 0)
//                    prev.next = null;
//                else
//                    break;
//                t = t.next;
//            }





            // following line is a placeholder for compilation

            //check for zeros
            for(int i = 0; i < userintfinal.numDigits;i++) {
                DigitNode n = userintfinal.front;
                DigitNode prev = userintfinal.front;
                while (n.next != null) {
                    prev = n;
                    n = n.next;
                }
                if (n.digit == 0) {
                    prev.next = null;
                    userintfinal.numDigits--;
                }
                else
                    break;


            }
            DigitNode trav = userintfinal.front;
            while(trav.next != null)
                trav = trav.next;
            if(trav.digit == 0) {
                userintfinal.front = new DigitNode(0, null);
                userintfinal.negative = false;
            }


            return userintfinal;


        }

        private static BigInteger multiplyHelper (BigInteger second, DigitNode n, int zC) {
            BigInteger i = new BigInteger();
            DigitNode temp2 = second.front;
            int carry = 0;

            int c = 0;
            while(c < zC){
                DigitNode v = new DigitNode(0, null);
                if(i.front == null){
                    i.front = v;
                    i.numDigits++;
                }
                else{
                    DigitNode t = i.front;
                    while(t.next != null)
                        t = t.next;
                    t.next = v;
                    i.numDigits++;
                }
                c++;
            }
            while (temp2 != null) {






                int tot = n.digit * temp2.digit + carry;
                if(tot > 9){
                    int vl = tot%10;
                    carry = tot/10;
                    DigitNode v = new DigitNode(vl, null);
                    if(i.front == null) {
                        i.front = v;
                        i.numDigits++;
                    }
                    else{
                        DigitNode t = i.front;
                        while(t.next != null)
                            t = t.next;
                        t.next = v;
                        i.numDigits++;
                    }
                }
                else{
                    carry = 0;
                    DigitNode v = new DigitNode(tot, null);
                    if(i.front == null){
                        i.front= v;
                        i.numDigits++;
                    }
                    else{
                        DigitNode t = i.front;
                        while(t.next != null)
                            t = t.next;
                        t.next = v;
                        i.numDigits++;
                    }

                }


                temp2 = temp2.next;

            }
            if(carry > 0){
                DigitNode v = new DigitNode(carry, null);
                DigitNode t = i.front;
                while(t.next != null)
                    t = t.next;
                t.next = v;
                i.numDigits++;
            }

            return i;

        }

        private static BigInteger copy(BigInteger v){
            DigitNode t = v.front;
            BigInteger x = new BigInteger();
            while( t != null){
                DigitNode n = new DigitNode(t.digit, null);
                if(x.front == null){
                    x.front = n;
                    x.numDigits++;
                }
                else{
                    DigitNode temp = x.front;
                    while(temp.next != null){
                        temp = temp.next;
                    }
                    temp.next  = n;
                    x.numDigits++;
                }

                t = t.next;
            }
            x.negative = v.negative;
            return x;
        }

        private static BigInteger substractionHelper(BigInteger larger, BigInteger smaller){
            BigInteger output = new BigInteger();

            BigInteger large = copy(larger);
            BigInteger small = copy(smaller);

            DigitNode l = large.front;
            DigitNode s = small.front;

            int borrow  = 0;

            //get first value
            int firstTotal = l.digit - s.digit;
            if(firstTotal >= 0){
                DigitNode total = new DigitNode(firstTotal ,null);
                output.front = total;
                output.numDigits++;

            }
            else{
                borrow = 1;
                firstTotal = l.digit + 10 -s.digit;
                DigitNode total = new DigitNode(firstTotal ,null);
                output.front = total;
                output.numDigits++;
            }
            l = l.next;
            s = s.next;
            while(s  != null){
                firstTotal = l.digit - s.digit - borrow;
                if(firstTotal >= 0){
                    DigitNode total = new DigitNode(firstTotal ,null);
                    borrow = 0;
                    DigitNode temp = output.front;
                    while (temp.next != null)
                        temp = temp.next;
                    temp.next = total;
                    output.numDigits++;
                }
                else{
                    firstTotal = l.digit + 10 -s.digit-borrow;
                    borrow = 1;
                    DigitNode total = new DigitNode(firstTotal ,null);
                    DigitNode temp = output.front;
                    while (temp.next != null)
                        temp = temp.next;
                    temp.next = total;
                    output.numDigits++;
                }

                l = l.next;
                s = s.next;
            }

            if(large.numDigits != small.numDigits){
                while(l != null){
                    firstTotal = l.digit - borrow;
                    if(firstTotal >= 0){
                        borrow = 0;
                        DigitNode total = new DigitNode(firstTotal ,null);
                        DigitNode temp = output.front;
                        while (temp.next != null)
                            temp = temp.next;
                        temp.next = total;
                        output.numDigits++;
                    }
                    else{
                        borrow = 1;
                        firstTotal = l.digit+10-borrow;
                        DigitNode total = new DigitNode(firstTotal ,null);
                        DigitNode temp = output.front;
                        while (temp.next != null)
                            temp = temp.next;
                        temp.next = total;
                        output.numDigits++;
                    }
                    l  = l.next;
                }
            }

            while(output.numDigits > 1){
                DigitNode temp = output.front;
                DigitNode prev = output.front;
                while(temp.next != null) {
                    prev = temp;
                    temp = temp.next;
                }
                if(temp.digit == 0){
                    prev.next = null;
                    output.numDigits--;
                }
                else
                    break;
            }
            DigitNode t = output.front;
            while(t.next != null)
                t = t.next;
            if(t.digit == 0){
                DigitNode zero = new DigitNode(0, null);
                output.front = zero;
                output.numDigits = 1;
                output.negative= false;
                return output;
            }

            output.negative = larger.negative;
            return output;
        }


        private static boolean firstIsBigger(BigInteger first, BigInteger second){
            boolean firstBig = true;
            if(first.numDigits > second.numDigits)
                return firstBig;
            else if (first.numDigits < second.numDigits)
                return false;
            else{
                BigInteger f = copy(first);
                BigInteger secon = copy(second);
                DigitNode s = secon.front;
                while(s != null) {
                    DigitNode tf = f.front;
                    DigitNode ts = secon.front;
                    DigitNode prev1 = f.front;
                    DigitNode prev2 = secon.front;
                    while(tf.next != null){
                        prev1 = tf;
                        prev2 = ts;
                        tf = tf.next;
                        ts = ts.next;
                    }
                    if(tf.digit > ts.digit)
                        return true;
                    else if(tf.digit < ts.digit)
                        return false;
                    else if(f.numDigits == 1)
                        if(f.front.digit >= secon.front.digit)
                            return true;
                        else
                            return  false;
                    else{
                        prev1.next = null;
                        f.numDigits--;
                        prev2.next = null;
                        secon.numDigits--;
                    }


                    s = secon.front;
                }

            }
            return firstBig;
        }


            /* (non-Javadoc)
             * @see java.lang.Object#toString()
             */
            public String toString () {
                if (front == null) {
                    return "0";
                }
                String retval = front.digit + "";
                for (DigitNode curr = front.next; curr != null; curr = curr.next) {
                    retval = curr.digit + retval;
                }

                if (negative) {
                    retval = '-' + retval;
                }
                return retval;
            }


}
