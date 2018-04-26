import java.util.Properties;
import java.io.*;
import org.omg.CORBA.*;
import StudentApp.*;
import org.omg.CosNaming.* ;
import org.omg.CosNaming.NamingContextPackage.*;


public class Client
{
    public static NamingContextExt rootCtx;

    public static void list(NamingContext n, String indent) {
        try {
                final int batchSize = 1;
                BindingListHolder bList = new BindingListHolder() ;
                BindingIteratorHolder bIterator = new BindingIteratorHolder();
                n.list(batchSize, bList, bIterator) ;
                if (bList.value.length != (int) 0)
                    for (int i = 0; i < bList.value.length; i++) {
                        BindingHolder bh = new BindingHolder(bList.value[0]);
                        do {
                            String stringName = rootCtx.to_string(bh.value.binding_name);
                            System.out.println(indent + stringName) ;
                            if (bh.value.binding_type.value() == BindingType._ncontext) {
                                String _indent = "----" + indent;

                                NameComponent [] name = rootCtx.to_name(stringName);
                                NamingContext sub_context = NamingContextHelper.narrow(n.resolve(name));
                                list(sub_context, _indent);
                            }
                        } while (bIterator.value.next_one(bh));
                    }
                else
                    System.out.println(indent + "Nothing more in this context.") ;
        }
        catch (Exception e) {
            System.out.println("An exception occurred. " + e) ;
            e.printStackTrace();
        }
    }

    public static void main(String args[])
    {
	     try{
	         // create and initialize the ORB
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            rootCtx = NamingContextExtHelper.narrow(objRef);

            // Call the list function to iterate through the Name Space
            list(rootCtx, "---->");
            System.out.println("Printing Done");

            NameComponent nc[]= new NameComponent[5];

            nc[0] = new NameComponent("Context1", "Context");
            nc[1] = new NameComponent("Sub-Context1", "Context");
            nc[2] = new NameComponent("Sub-Sub-Context1", "Context");
            nc[3] = new NameComponent("Sub-Sub-Sub-Context1", "Context");
            nc[4] = new NameComponent("Object2", "Object");

            org.omg.CORBA.Object answersObjRef = rootCtx.resolve(nc);

            StudentInfo siRef = StudentInfoHelper.narrow(answersObjRef);

            Student stu= new Student();
            StudentHolder holder = new StudentHolder();

            stu.grade1 = 10;
            stu.grade2 = 20;

            holder.value = stu;

    int result = siRef.totalGrade(holder);
            System.out.println("Total grade is "+result);
            System.out.println("The value of grade 1 is "+stu.grade1);
            System.out.println("The value of grade 2 is "+stu.grade2);
            System.out.println("The value of grade 1 in holder is "+holder.value.grade1);//0 coming back
            System.out.println("The value of grade 2 in holder is "+holder.value.grade2);

            } catch (Exception e) {
                System.out.println("ERROR : " + e) ;
                e.printStackTrace(System.out);
            }
	}
}
