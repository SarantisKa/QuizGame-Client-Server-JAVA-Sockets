import java.util.ArrayList;

public class AllQuestions {
	
	
    protected static ArrayList<Question> allQuestions = new ArrayList<Question>();

	public static void addQuestion(Question question) {
		allQuestions.add(question);
	}
    
}
