import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class App {
    public static int[][] readAndStore(String files) throws Exception{
        File file = new File(files);
        FileReader fileReader = new FileReader(file);
        String st;
        BufferedReader br = new BufferedReader(fileReader);
        StringBuffer sb=new StringBuffer();
        st = br.readLine();
        String s = " ";
        while (st != null) {
            sb.append(st);
            sb.append("\n");
            s += st + " ";
            st = br.readLine();
        }
        String str = sb.toString();
        String[] list = str.split("\n");
        int n = list.length;
        int [][] arr = new int [n][n];
        for (int i=0; i<arr.length; i++){
            String[] subList = list[i].split(" ");
            for (int j=0; j< subList.length;j++){
                arr[i][j]= Integer.parseInt(subList[j]);
            }
        }
        return arr;
    }
    public static Boolean isPrime(int n){
        boolean num = true;
        if(n <= 1) num = false;
        for (int i = 2; i< n; i++){
            if ((n % i) == 0){
                num = false;
                break;
            }
        }
        return num;
    }
    public static int findMaxSum(int [][] arr){
        //tempArr is copy of arr
        int [][] tempArr = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < i+1; j++){
                tempArr[i][j] = arr[i][j];
            }
        }
        int max;
        for (int i= arr.length-1; i>=0; i--){
            if (i != 0){
                for (int j=0; j< i+1 ; j++){
                    //System.out.println("temporary array: "+i +"-"+j+" -> "+tempArr[i][j]);
                    //System.out.println("base array: "+i +"-"+j+" -> "+arr[i][j]);
                    if (j+1 < i+1){
                        if (!isPrime(arr[i][j]) && !isPrime(arr[i][j+1])){
                            max = Math.max(tempArr[i][j],tempArr[i][j+1]);
                            tempArr[i-1][j] += max;
                        }else if(!isPrime(arr[i][j]) && isPrime(arr[i][j+1])){
                            tempArr[i-1][j] += tempArr[i][j];
                        }else{
                            tempArr[i-1][j] += tempArr[i][j+1];
                        }
                    }
                }
            }
        }
        int result =tempArr[0][0];
        return result;
    }
    public static void main(String[] args) throws Exception {
        int maxSum = findMaxSum(readAndStore(args[0]));
        System.out.println("Maximum sum is : " + maxSum);
        // if we run this project in ide , we get error because of args expression so we use file path instead of args
        // project should be run correctly
        // findMaxSum(readAndStore("C:\\Users\\User\\   ...   \\src\\triangle.txt"));
    }
}


