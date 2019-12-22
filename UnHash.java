import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class UnHash
{
    public static int[] unhashCompoundSimple(String to_unhash, HashMap<Long,String> hashDict)
    {
        int offset1 = 0, offset2 = 0, offset3 = 0, offset4 = 0;

        long ten3 = (long) Math.pow(10,3);
        long ten6 = (long) Math.pow(10,6);
        long ten9 = (long) Math.pow(10,9);

        int simpleCnt = 0;
        int limit = 1;

        while (limit < 61)
        {
            for (offset4 = 1; offset4<=limit; offset4++)
            for (offset3 = 1; offset3<=limit; offset3++)
            for (offset2 = 1; offset2<=limit; offset2++)
            for (offset1 = 1; offset1<=limit; offset1++)
            {
                if (offset1<limit && offset2<limit && offset3<limit && offset4<limit)
                    continue;
                    
                long compound = offset1 + ten3*offset2 + ten6*offset3 + ten9*offset4;

                if (simpleCnt < 100000)
                {
                    String simpleHashed = hashDict.get(new Long(simpleCnt));
                    if (simpleHashed == null)
                    {
                        simpleHashed = Hash.hash(String.valueOf(simpleCnt));
                        hashDict.put(new Long(simpleCnt), simpleHashed);
                    }

                    if (simpleHashed.equals(to_unhash))
                    {
                        return new int[]{simpleCnt};
                    }
                    simpleCnt++;
                }

                String compHashed;
                if (compound < 1010001000)
                {
                    compHashed = hashDict.get((Long)compound); 
                    if (compHashed == null)
                    {
                        compHashed = Hash.hash(String.valueOf(compound));
                        hashDict.put((Long)compound, compHashed);
                    }
                }
                else
                {
                    compHashed = Hash.hash(String.valueOf(compound));
                }
                if (compHashed.equals(to_unhash))
                {
                    return new int[] {offset1, offset2, offset3, offset4};
                }
            }
            limit++;
        }
        return new int[] {-1,-1,-1,-1};
    }
}