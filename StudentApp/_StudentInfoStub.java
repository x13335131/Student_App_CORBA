package StudentApp;


/**
* StudentApp/_StudentInfoStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Test.idl
* 18 April 2018 19:09:06 o'clock IST
*/

public class _StudentInfoStub extends org.omg.CORBA.portable.ObjectImpl implements StudentApp.StudentInfo
{

  public int totalGrade (StudentApp.StudentHolder stu)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("totalGrade", true);
                StudentApp.StudentHelper.write ($out, stu.value);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                stu.value = StudentApp.StudentHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return totalGrade (stu        );
            } finally {
                _releaseReply ($in);
            }
  } // totalGrade

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:StudentApp/StudentInfo:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _StudentInfoStub
