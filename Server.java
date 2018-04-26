import StudentApp.*; //package containing our stubs
import org.omg.CORBA.*; //access corba content
import java.io.*; //access to input/output methods
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import java.util.Properties.*;

public class Server {

    public static void main(String args[]){
      //using try/catch for error handling
      try{
        //~naming sevice content
        NameComponent nc[] = new NameComponent[1];

        //~end of naming service content
          ORB orb = ORB.init(args, null); //initialising the object request broker
          Servant servantRef = new Servant();//instantiating servant on the Server
          orb.connect(servantRef); //connecting servant to the orb
          System.out.println("ORB connected "+orb);

          //~naming services content~
          org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");//Obtaining object reference
          System.out.println("Found NameService");

          NamingContext rootCtx = NamingContextHelper.narrow(objRef); //coverting reference into a naming context reference

          //Placing server object using naming context
          //bound to root
          nc[0] = new NameComponent("Context5", "Context"); // NameComponent(ID, Kind(Context/Object));
          NamingContext context5 = rootCtx.bind_new_context(nc);

          nc[0] = new NameComponent("Context1", "Context");
          NamingContext context1 = rootCtx.bind_new_context(nc);

          nc[0] = new NameComponent("Context3", "Context");
          NamingContext context3 = rootCtx.bind_new_context(nc);


          //bound to context 5
          nc[0] = new NameComponent("Sub-Context5", "Context");
          NamingContext subContext5 = context5.bind_new_context(nc);

          nc[0] = new NameComponent("Object1", "Object");
          context5.rebind(nc,servantRef);

          //bound to context 1
          nc[0] = new NameComponent("Sub-Context1", "Context");
          NamingContext subContext1 = context1.bind_new_context(nc);

          nc[0] = new NameComponent("Object2", "Object");
          context1.rebind(nc,servantRef);

          //bound to context 3
          nc[0] = new NameComponent("Sub-Context3", "Context");
          NamingContext subContext3 = context3.bind_new_context(nc);

          nc[0] = new NameComponent("Object4", "Object");
          context3.rebind(nc,servantRef);

          //bound to subcontext5
          nc[0] = new NameComponent("Object1", "Object");
          subContext5.rebind(nc,servantRef);

          //bound to subContext1
          nc[0] = new NameComponent("Sub-Sub-Context1", "Context");
          NamingContext subSubContext1 = subContext1.bind_new_context(nc);

          //bound to subContext3
          nc[0] = new NameComponent("Object3", "Object");
          subContext3.rebind(nc,servantRef);

          //bound to subSubContext1
          nc[0] = new NameComponent("Sub-Sub-Sub-Context1", "Context");
          NamingContext subSubSubContext1 = subSubContext1.bind_new_context(nc);

          //bound to subSubSubContext1
          nc[0] = new NameComponent("Object2", "Object");
          subSubSubContext1.rebind(nc,servantRef);


          //~end of naming services content~


          orb.run(); //waiting on calls for client

      }catch(Exception e){ //produce exception if above doesnt work
        System.err.println("Error: "+e);
        e.printStackTrace(System.out);
      }
    }
}
