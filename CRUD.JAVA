import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
class DAO
{
    Connection con; //it is a connection type variable
    DAO() throws ClassNotFoundException,SQLException //Defaultconstructor we are throwing un reported exceptions
    {                                
        Class.forName("com.mysql.cj.jdbc.Driver");//load driver
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gecdb2?serverTimezone=UTC","root","");
    }
    void insert(int eno,String ename)throws SQLException//instance method
    {
        Statement stmt= con.createStatement();
        int r=stmt.executeUpdate("insert into emp1 values("+eno+",'"+ename+"')");
        if(r==1)
        {
            System.out.println("inserted successfully");
        }
        else
        {
            System.out.println("insertion failed");
        }
    }
   void updateName(int eno,String ename) throws SQLException
   {
       String cmd ="update emp1 set ename=? where eno=?";
        PreparedStatement pstmt=con.prepareStatement(cmd);
        pstmt.setString(1,ename);
        pstmt.setInt(2,eno);
        int r=pstmt.executeUpdate();
        if(r==1)
        {
           System.out.println("updated  name.......");
        }
        else
        {
             System.out.println("update failed");
        }
    }
    void delete(int eno) throws SQLException
    {
        String cmd="delete from emp1 where eno=?";
        PreparedStatement pstmt=con.prepareStatement(cmd);
        pstmt.setInt(1,eno);
        int r=pstmt.executeUpdate();
        if(r==1)
        {
            System.out.println("deleted successfully");
        }
        else
        {
            System.out.println("deletion failed");
        }
    }
    void viewAll()throws SQLException
    {
        String cmd="select * from emp1";
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(cmd);//result object contain all the rows
        while(rs.next())//check for the next row
        {
            System.out.printf("%-5d %-30s %n",rs.getInt("eno"),rs.getString("ename"));
        }
    }
}
public class CRUD
{
    public static void main(String[] args) throws Exception
    {
        DAO obj = new DAO();// it returns object address of the DAO that is 1002
        // obj.insert(8,"ms");
        // obj.insert(9,"Surya");
        // obj.insert(10,"Viraj");
        // obj.updateName(1,"Hardhik");
        // obj.delete(8);
        //obj.viewAll();
        do
        {
            System.out.println("1.insert 2.update 3.delete 4.viewAll 5.exit");
            Scanner scan=new Scanner(System.in);
            int opt=scan.nextInt();
            switch(opt)
            {
                case 1:
                obj.insert(11,"Goutham");
                break;
                case 2:
                obj.updateName(11,"GouthamNaandha");
                break;
                case 3:
                obj.delete(10);
                break;
                case 4:
                obj.viewAll();
                break;
                case 5:
                System.out.println("exit");
                break;
            }
            System.out.println("Continue(1/0)");
            int r=scan.nextInt();
            if(r==0)
            {
                System.out.println("exit");
                break;
            }
        }while(true);
    }
}
