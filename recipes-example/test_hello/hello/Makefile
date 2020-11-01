hello: hello.o
	$(CC) -o hello hello.o
	
	
hello.o: hello.c
	$(CC) -c hello.c
	
.PHONY: clean
clean:
	-rm -rf *.o
