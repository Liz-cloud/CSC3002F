JAVAC=/usr/bin/javac
SRCDIR=	src
BINDIR=	bin
docdir=	./docs

.SUFFIXES:	.java	.class

.java.class:
	$(JAVAC)	$<

default:
	$(JAVAC)	-d	$(BINDIR)	$(SRCDIR)/*.java
clean:
	rm	$(BINDIR)/*.class
run2:
	java	-cp	bin OS1Assignment "OS1sequence"
docs:
	javadoc	-d	$(docdir)	$(SRCDIR)/*.java

cleandocds:
	rm	-r	docs/*