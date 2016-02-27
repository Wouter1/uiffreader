# UIFF Reader

This is a tool to read in uiff/tvd files. 

There is also included a sample program that reads a specified file, converts all contained images to png, and spits out a structure description.

Other programs can use extend this tool, to extract information like images from the uiff file.

The extension of an uiff file can probably be anything, you need to check the first bytes in the actual data by opening the file in a simple text editor. If it starts with "UIFF" and you see some other 4-character, this tool may be the way to accessing the contents.

This format was reverse engineered from a single ultrasound video coming from a Telemed Ultrasound scanner.
Although it works for my single tvd file, I probably guessed a few things wrong. Please report if you have troubles.

## System Requirements
You need to have at least java 1.6 installed. You can check which version is on your computer by executing ```java --version``` on the command line.  It will show something like ```java version "1.7.0_67"``` which is java 1.7. 

## Usage

TO convert a .tvd file into a set of images
1. download (from releases) or build the convert.jar
2. from the console, execute ```java -jar convert.jar <path to your tvd file>``` in the directory where you downloaded the jar. 
3. The images contained in the .tvd file will be written into a new created directory named "images"
4. The structure of the tdv will be printed to the command line.

