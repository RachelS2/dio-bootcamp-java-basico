import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SelectiveProcess {
    Double BASE_SALARY=  2000.00;
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        SelectiveProcess process = new SelectiveProcess();
        List<String> strongCandidates = process.CandidatesAnalysis(input);
        process.ViewSelectedCandidates(strongCandidates);
        input.close();
    }

    public List<String> CandidatesAnalysis(Scanner input){

        System.out.println("Base Salary = " + BASE_SALARY);
        List<String> strongCandidates = new ArrayList<>();

        while (strongCandidates.size() < 5)
        {
            System.out.print("Candidate Name: ");
            String candidateName = input.next();
            int intendedSalary = IntendedSalary();
            System.out.print("Salary intended by the candidate " + candidateName + ": " + intendedSalary);

            if (BASE_SALARY >= intendedSalary) 
            {
                System.out.println("\nCONTACT CANDIDATE");
                strongCandidates.add(candidateName);
            }
            
            else if (BASE_SALARY < intendedSalary) 
                System.out.println("\nCONTACT CANDIDATE WITH A COUNTER OFFER");
            
            else 
                System.out.println("\nWAITING OTHER CANDIDATES RESULT");
            
            System.out.println("\n-------------------------------------------------\n");
        }

        return strongCandidates;
    }
    
    public int IntendedSalary()
    {
        return ThreadLocalRandom.current().nextInt(1000, 3000);
    }

    public void ViewSelectedCandidates(List<String> selectedCandidates){
        System.out.println("THE SELECTED CANDIDATES ARE: \n");
        for (String candidate: selectedCandidates){
            System.out.println(candidate);
        }

        ContactCandidates(selectedCandidates);
    }

    public void ContactCandidates(List<String> selectedCandidates){    
        for (String candidate: selectedCandidates){
            int numberOfCalls = 0;
            boolean success;
            do 
            {
                success = CallingCandidate(candidate);
                numberOfCalls++;
            }
            while (numberOfCalls < 4);         

            if (success == true)
                System.out.println("The candidate " + candidate + " was contacted after " + (numberOfCalls-1) + " calls.");
            else 
                System.out.println("The candidate " + candidate + " couldn't be contacted.");
        }
        
    }

    public boolean CallingCandidate(String candidate){
        return new Random().nextInt(3) == 1; 
        // if the method returns 1, it means the candidate has accepted the phone call.
    }
}
