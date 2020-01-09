<H1> Task 1 </H1> 
 
<H2> Problem assigned </H2>
Check on GitLab for a folder containing example images. These images are taken
from several open and free studies, articles or other sources. Some of them have explicit or implicit meta data annotations.<br> 
    1. Check the meta data (EXIF, filename, ...) and write a concept which metadata needs to be passed as additional information. <br> 
    2. Your application should take several command line parameters. --import should take a image file name. --print is an option that prints all meta information on the screen without doing anything more. Define – and doc- ument! – the parameter --meta which takes additional meta information. How should user pass these information? You can also split this up in several parameters. <br> 

    
<H1> Installation </H1> 

Steps to get the application running
1. Git clone the repository
2. Open Eclipse 
    *  Direct the workspace to /group-03-descartes/ProgrammingProject05
    *  Import existing maven project
    *  Select the de.bit.pl02.task01 pom xml for importing the existing maven project. 
    *  Do a maven install 
3. After the application is installed you will see a task01-0.0.1-SNAPSHOT.jar in the /target folder
<H1> 
4. To execut the jar use the following command
    *  java -cp <Path to Programming Project>/ProgrammingProject05/Task01/task01/target/task01-0.0.1-SNAPSHOT.jar  de.bit.pl02.pp5.task01.CommandLineInterface __\<options>__ __\<arguments>__

     

<H1> Description </H1> 
<H3> Main class </H3> 
<B>Command Line Interface</B> <br> 
      Parameters from command line <br> 

*   <B>    -d or --directory</B>   : for specifying the path to the directory containing the images <br>
*   <B>    -ip or --inputfile</B>  : the name of the input file you want to deal with <br>  
*   <B>    -p or --print</B>       : if you want to print the meta information or not <br>  
*   <B>    -m or --meta</B>        : if you want to add the meta information <br> 
*   <B>    -im or --inputmeta</B>  : the values of metainformation you want to enter <br>

    Mandatory commands needed for the application to run <br> 
*   <B>    -d or --directory</B> <br>

   

<H3> Subsidiary Class </H3> 
Metadata

<H1> Prerequisites </H1> 

<H2> Dependencies </H2> 
*       <B>    Apache Commons cli </B> -- version 1.4   <br> 
*       <B>     Apache commons lang3 </B> -- version 3.4   <br>
<H2> Plugins </H2> 
*       <B>    Maven jar plugin </B>   <br> 
*       <B>    Maven shade plugin </B>    <br>  


<H1> Built With </H1> 
