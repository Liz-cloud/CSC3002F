# OS2 Assignment

# RunSimulation class:
1. The code was edited to make sure that the carbon atoms and hydrogen atoms match 
   and that no extra atoms are left not bonded. THis is done by making sure that the factor for hydrogen and carbon are the same
   
# Carbon.run() Pseudo code
1. mutex.acquire() // set it to 0 lock it 
2. carbon+=1   //increment number of carbon atoms
3. if(hydrogen>=8)
4.    print statement
5.    hydrogenQueue.release(8) //add 8 hydrogen atoms to queue
6.    hydrogen-=8  // release the 8 hydrogen atoms
7.    carbonQueue.release(3)  //add the carbon atoms to queue
8.    carbon-=3 // release the 3 carbon atoms
9.  else 
10.   mutex.release() // set to 1 open it 
11.  carbonQueue.acquire() //set to 0 to lock it
12.  bond()
13.  barrier.wait() 
14.  mutex.release()

# Hydrogen.run() Pseudo code
1. mutex.acquire() // set it to 0 lock it
2. hydrogen+=1   //increment number of hydrogen atoms
3. if(hydrogen>=8 and carbon>=3 )
4.    print statement
5.    hydrogenQueue.release(8) //add 8 hydrogen atoms to queue
6.    hydrogen-=8  // release the 8 hydrogen atoms
7.    carbonQueue.release(3)  //add the carbon atoms to queue
8.    carbon-=3 // release the 3 carbon atoms
9.  else
10.   mutex.release() // set to 1 open it
11.  hydrogenQueue.acquire() //set to 0 to lock it
12.  bond()
13.  barrier.wait()
14.  mutex.release()

# Testing:
When run the RunSimulation for the first time it seems to hang like it won't end its execution
but after you run it again it will start working probably a deadlock happening 