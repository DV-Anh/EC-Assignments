package Selection;

import java.util.Random;

public class TournamentSelection {
    /**
     * @param matingPool
     * @param selectionNum
     * @return
     */
    public double[] selection(double[] matingPool, int selectionNum,int tournamentSize )
    {
        Random random=new Random();
        double[] selectedElements =new double[selectionNum];
        double[] participants=new double[tournamentSize];
        int currentMembersNum=0;
        while (currentMembersNum<selectionNum)
        {
            int[] indexOfIndividuals=new int[tournamentSize];
            int currentRandomNum=0;
            while(currentRandomNum<tournamentSize)
            {
                int randomNum=random.nextInt(matingPool.length);
                if(!whetherInArray(indexOfIndividuals,randomNum))
                {
                    indexOfIndividuals[currentRandomNum]=randomNum;
                    currentRandomNum++;
                }
            }
            for (int i = 0; i <tournamentSize; i++) {
                participants[i]=matingPool[indexOfIndividuals[i]];
            }
            selectedElements[currentMembersNum]=participants[hightestFitness(participants)];
            currentMembersNum++;

        }
        return selectedElements;
    }

    /**
     * Using f(x)=x as fitness function
     * @param elements
     * @return
     */
    public int hightestFitness(double[] elements)
    {
        double[] temp=new double[elements.length];

        for (int i = 0; i < elements.length; i++) {
            temp[i]=fitness(elements[i]);
        }
        int aar_index = 0;
        if(temp.length>0) {
            double aar_Max = temp[0];

            for (int i = 0; i < temp.length; i++) {
                if (temp[i] > aar_Max) {//比较后赋值
                    aar_Max = temp[i];
                    aar_index = i;
                }
            }
        }
        return aar_index;
    }

    public double fitness(double element)
    {
        return element;
    }
    public static boolean whetherInArray(int[] arr, int targetValue) {
        for(int s: arr){
            if(s==targetValue)
                return true;
        }
        return false;
    }

    public static void main(String[] args)
    {
        TournamentSelection a=new TournamentSelection();
        double[] ss=a.selection(new double[] {5,18,91,23,66,56,45,76,235},3,2);
        for(double sss:ss)
        {
            System.out.println(sss);
        }
    }
}
