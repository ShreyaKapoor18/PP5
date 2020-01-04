<H1> Task 1 </H1> 
<br> 
Check on GitLab for a folder containing example images. These images are taken
from several open and free studies, articles or other sources. Some of them have explicit or implicit meta data annotations.<br> 
    1. Check the meta data (EXIF, filename, ...) and write a concept which metadata needs to be passed as additional information. <br> 
    2. Your application should take several command line parameters. --import should take a image file name. --print is an option that prints all meta information on the screen without doing anything more. Define – and doc- ument! – the parameter --meta which takes additional meta information. How should user pass these information? You can also split this up in several parameters. <br> 

    
<H1> Getting Started </H1> 

<H1> Description </H1> 
<H5> Main class </H5> <br> 
Command Line Interface 
    Parameters from command line 
    *  -d or --directory : for specifying the path to the directory containing the images
    * -ip or --inputfile : the name of the input file you want to deal with 
    * -p or --print      : if you want to print the meta information or not 
    * -m or --meta       : if you want to add the meta information
    * -im or --inputmeta : the values of metainformation you want to enter
    Mandatory commands needed for the application to run
    * -d or --directory 
    * -ip or --inputfile 
    
<H5> Subsidiary Class </H5> 
Metadata

<H1> Prerequisites </H1> 

<H2> Dependencies </H2> 
*  Apache Commons cli -- version 1.4 
*  Apache commons lang3 -- version 3.4 
*  Maven jar plugin 
<H1> Running tests </H1> 

<H1> Built With </H1> 
