import java.util.ArrayList;

public class Question {
	
	private String question;
	private String answerA;
	private String answerB;
	private String answerC;
	private String correctAnswer;
	
	public Question(String question, String answerA, String answerB, String answerC, String correctAnswer) {
		this.question = question;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.correctAnswer = correctAnswer;
		AllQuestions.addQuestion(this);
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswerA() {
		return answerA;
	}

	public String getAnswerB() {
		return answerB;
	}

	public String getAnswerC() {
		return answerC;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public Question() {
	}
	
	
}
