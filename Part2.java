import edu.duke.*; 

/**
* 
* @author: Amir Armion 
* 
* @version: V.01
* 
*/
public class Part2 
{
    StorageResource genes = new StorageResource();

    public double cgRatio(String mainDna)
    {
        String dna    = mainDna.toUpperCase();
        double length = dna.length();
        int    count  = 0;

        for(char letter: dna.toCharArray())
        {
            if(letter == 'C' || letter == 'G')
            {
                count++;
            }
        }

        return count / length;
    }

    public int countCTG(String mainDna)
    {
        String dna        = mainDna.toUpperCase();
        int    startIndex = 0;
        int    index      = 0;
        int    count      = 0;

        while(index != -1)
        {
            index = dna.indexOf("CTG", startIndex);

            if(index == -1)
            {
                return count;
            }

            startIndex = index + 3;
            count++;
        }

        return count;
    }

    public void  processGenes(StorageResource sr)
    {
        int count1  = 0;
        int count2  = 0;
        int longest = 0;

        for(String string: sr.data())
        {
            if(string.toUpperCase().length() > 60)
            {
                System.out.println(string.toUpperCase());
                count1++;
            }

            if(cgRatio(string.toUpperCase()) > 0.35)
            {
                System.out.println(string.toUpperCase());
                count2++;
            }

            if(string.length() > longest)
            {
                longest = string.length();
            }
        }

        System.out.println("\nNumber of strings that are longer than 60 characters: " + count1);

        System.out.println("\nNumber of strings that CG-Ratio is higher than 0.35: " + count2);

        System.out.println("\nLongest Gene: " + longest);
    }

    public void testProcessGenes()
    {
        FileResource fr  = new FileResource("brca1line.fa.txt");
        String       dna = fr.asString();
        Part1        p1  = new Part1();

        p1.genes.clear();
        System.out.println("Number of CTG: " + countCTG(dna));

        p1.genes.clear();
        processGenes(p1.getAllGenes(dna));

        p1.genes.clear();
        System.out.println("\nNumber of Genes are: " + p1.getAllGenes(dna).size());
    }
}
