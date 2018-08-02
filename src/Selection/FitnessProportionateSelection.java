package Selection;




public class FitnessProportionateSelection {
    public double[] selection(double[] matingPool, int selectionNum)
    {

        double sum=0;
        for (int i = 0; i <matingPool.length; i++)
        {
            sum=sum+matingPool[i];
        }

        double[] selectedElements =new double[selectionNum];
        int currentMembersNum=0;
        while (currentMembersNum<selectionNum)
        {
            int indexOfArrays=0;
            double random=Math.random();
            while( matingPool[indexOfArrays]/sum < random)//数组越界
            {
                indexOfArrays++;
                if(indexOfArrays+1>=matingPool.length)
                {
                    break;
                }
            }
            if(!whetherInArray(selectedElements,matingPool[indexOfArrays])) {
                selectedElements[currentMembersNum] = matingPool[indexOfArrays ];
                currentMembersNum++;
            }
        }

        return selectedElements;
    }

    /**
     * Using f(x)=x as fitness function
     * @param matingPool
     * @return
     */
    public double probabilitiesOfElement(double[] matingPool, int num)
    {
        double sum=0;
        for (int i = 0; i <matingPool.length; i++)
        {
            sum=sum+matingPool[i];
        }

        return matingPool[num]/sum;
    }

    public static void main(String[] args)
    {
        FitnessProportionateSelection a=new FitnessProportionateSelection();
        double[] ss=a.selection(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},5);
        for(double sss:ss)
        {
            System.out.println(sss);
        }
    }
    public static boolean whetherInArray(double[] arr, double targetValue) {
        for(double s: arr){
            if(s==targetValue)
                return true;
        }
        return false;
    }
}
