import StudentApp.*;

class Servant extends _StudentInfoImplBase {
//methods
public int totalGrade(StudentApp.StudentHolder g)
    {
		System.out.println("Received a call.");

    Student val = g.value;

    int result = val.grade1+val.grade2;

    val.grade1=0;
    val.grade2=0;
    
		return result;
    }
}
