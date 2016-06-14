package com.ithxh.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestNetSpeed {

	public static void main(String args[])

    {

    String[] addrs= {"http://www.baidu.com"};

        if (addrs.length < 1)

        {

            System.out.println("syntax Error!");

        }

        else

        {
        	Runtime rt = Runtime.getRuntime();
            for(int i=0;i<addrs.length;i++){

            String line = null;

            try

            {
            	
                Process pro = rt.exec("ping www.baidu.com -l 1000 -n 4");
                System.out.println(pro.exitValue());
                BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream()));
                int exitVal = pro.waitFor(); 
                while((line = buf.readLine()) != null){
                	System.out.println(line);
                 

                 int position=0;

                 if((position=line.indexOf("Average"))>=0)      

                 {  System.out.println(line);

                 String value=line.substring(position+10,line.lastIndexOf("ms"));

                 System.out.println("your speed is:"+(1000/Integer.parseInt(value))+"KB");              

                 }
                 exitVal = pro.waitFor();  
                 System.out.println("Process exitValue: " + exitVal); 

                }        

            }

            catch(Exception ex)

            {

                System.out.println(ex.getMessage());

            }

          }

        }

      }

}