# UIFF Reader

This is a tool to read in uiff/tvd files. The 'read' program just reads the file and spits out a structure description.
Other programs can use extend this tool, to extract information like images from the uiff file.

The extension of an uiff file can probably be anything, you need to check the first bytes in the actual data by opening the file in a simple text editor. If it starts with "UIFF" and you see some other 4-character, this tool may be the way to accessing the contents.

This format was reverse engineered from a single ultrasound video coming from an Telemed Ultrasound scanner.
Although it works for my single tvd file, I probably guessed a few things wrong. Please report if you have troubles.

I found that reading in a 80Mb uiff file takes much more memory than that. It seems int arrays are not stored very efficiently in Java. Give the JVM at least 512Mb using ```-Xmx512m```
