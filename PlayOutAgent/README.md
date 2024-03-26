# PlayOut Agent

The task is to debug and get the Java Agent upto date

## Sub tasks
* Updated build.xml to javac source and target 1.8, few deprecated calls are being made in the source code which need to be taken care of
* Updated asm library, modified NameExtractor.java in Normalizer/lib, Understood how and what instrumentation is being added to log reflective calls
* Yet to understand the intricacies of Hasher and ReflLogger.java post which updates can be swiftly done to bring PlayOutAgent's reflection logger upto date
* Yet to figure out if ClassDumper of PlayOut agent needs any changes
