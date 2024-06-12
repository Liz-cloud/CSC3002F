# Makefile Assignment 2
JAVAC=/usr/bin/javac
SRCDIR=molecule
BINDIR=bin
docdir= ./docs

.SUFFIXES:	.java	.class

.java.class:
	$(JAVAC)	$<

all:
	$(JAVAC)	-d	$(BINDIR)	$(SRCDIR)/molecule/*.java

clean:
	rm $(BINDIR)/*.class

run:
	java -cp bin molecule.RunSimulation 24 9

docs:
	javadoc	-d	$(docdir)	$(SRCDIR)/molecule/*.java

clean_docs:
	rm	-r	docs/*


