
import java.net.*;
import java.nio.Buffer;
import java.util.ArrayList;

import javax.print.DocFlavor.READER;

import java.io.*;

public class TCPServer {

	public static void main(String args[]) {
         try {
       	    ConstructQuestions questions = new ConstructQuestions();
            int serverPort = 7596;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
			    Connection c = new Connection(clientSocket);
		  }
		} catch (IOException e) {System.out.println("Listen:"+e.getMessage());}        
	}
}

class Connection extends Thread {
	Socket clientSocket;
	String data;
	String responceData;
	DataOutputStream out;
	DataInputStream in;
	public Connection (Socket aClientSocket) {
		try {
			clientSocket=aClientSocket;
            out = new DataOutputStream(clientSocket.getOutputStream());
            in =new DataInputStream(clientSocket.getInputStream());
			this.start();
		} catch (IOException e) {System.out.println("Connection:"+e.getMessage());}
		}
public void run() {
	try {
	  data = in.readUTF();
	  if (data.equals("start")){
		int index = 0, x=0, qn=1;
		// Create ArrayList with 5 random questions 
		 ArrayList<Question> randomQuestions = new ArrayList<>();
		 while(randomQuestions.size()!=5) {
	     index = (int) (Math.random()*AllQuestions.allQuestions.size());
	     Question rQ = AllQuestions.allQuestions.get(index);
	       if (!randomQuestions.contains(rQ)) {
	           randomQuestions.add(rQ);
	       }
		 }
		index=0;
		Question rq = randomQuestions.get(index);
		out.writeUTF(rq.getQuestion()+"\n"+"Α) "+ rq.getAnswerA()+"\nΒ) "+ rq.getAnswerB()+"\nC) "+ rq.getAnswerC());
		out.flush();
	while(!data.equals("close")){
	  if  (index==5){
    	out.writeUTF("Τέλος παιχνιδιού! \n Το αποτέσμα σας είναι: "+x+"/5. \n Για νέο παιχνίδι πιέστε START!");
    	data="close";
    	break;
      }
      out= new DataOutputStream(clientSocket.getOutputStream());
      in= new DataInputStream(clientSocket.getInputStream());
      responceData = in.readUTF();
       if (responceData.equals("A")||responceData.equals("B")||responceData.equals("C")){
		  if (responceData.equals(rq.getCorrectAnswer())){
		   x++;
	       out.writeUTF("Σωστή απάντηση!");
	       out.flush();
		  }
		  else {
		   out.writeUTF(" Λάθος! Η σωστή απάντηση είναι: "+rq.getCorrectAnswer());
		   out.flush();
		}
      }
      if (responceData.equals("n")) {
		index++;
		qn++;
		if(index<=4) {
		rq = randomQuestions.get(index);
		out.writeUTF("Ερώτηση:"+qn+"\n"+rq.getQuestion()+"\n"+"Α) "+ rq.getAnswerA()+"\nΒ) "+ rq.getAnswerB()+"\nC) "+ rq.getAnswerC());
		out.flush();
		}
      }

	}
	      }
	}catch (EOFException e) {System.out.println("EOF:"+e.getMessage());
	}catch (IOException e) {System.out.println("IO:"+e.getMessage());
	}finally {try {clientSocket.close();}catch (IOException e) {/*close failed*/}}
	}
}
