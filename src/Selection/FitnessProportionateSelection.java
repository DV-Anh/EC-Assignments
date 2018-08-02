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
        int indexOfArrays=0;
        while (currentMembersNum<selectionNum)
        {
            while(matingPool[indexOfArrays%matingPool.length]/sum < Math.random())//数组越界
            {
                indexOfArrays++;
            }
            if(!whetherInArray(selectedElements,matingPool[indexOfArrays%matingPool.length])) {
                selectedElements[currentMembersNum] = matingPool[indexOfArrays % matingPool.length];
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
        double[] ss=a.selection(new double[] {5,18,91,23,66,56,45,76,235},3);
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
