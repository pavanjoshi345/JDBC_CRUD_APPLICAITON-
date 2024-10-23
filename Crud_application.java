package First_code;
import java.sql.*;
import java.util.*;
public class CRUD_APPLICATION 
{
	public static void main(String[] args) throws SQLException
	{
		Scanner sc=new Scanner(System.in);
		com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(d);
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","1234");
		if(conn!=null)
		{
			System.out.println("Database Connected Successfully...");
			Statement stmt=conn.createStatement();
			int choice;
			do
			{
				System.out.println("Enter the your choice");
				System.out.println("1: Insert Record");
				System.out.println("2: Display Records");
				System.out.println("3: Delet Using Player Name ");
				System.out.println("4: Update By Using Name");
				System.out.println("5: Search Using Playr name");
				choice=sc.nextInt();
				switch(choice)
				{
				case 1:
					String name;
					int runs;
					System.out.println("Enter the name and runs of player");
					name=sc.next();
					runs=sc.nextInt();
				    int val=stmt.executeUpdate("insert into player values('0','"+name+"',"+runs+")");
				    if(val>0)
				    {
				    	System.out.println("Data Insert Successfully...");
				    }
				    else
				    {
				    	System.out.println("Data Not Insert Successfully...");
				    }
				    break;
				case 2:
					ResultSet rs=stmt.executeQuery("select *from player");
					System.out.println("ID\t\t Name\t\t Runs");
					System.out.println("----------------------------------------------------");
					while(rs.next())
					{
					   System.out.println(rs.getInt("pid")+"\t\t"+rs.getString("name")+"\t\t"+rs.getInt("runs"));
					}
					break;
				case 3:
					 System.out.println("Enter the Player Name");
					 String dname=sc.next();
					 int vl=stmt.executeUpdate("delete from player where name='"+dname+"' ");
					 if(vl>0)
					 {
						 System.out.println("Player Record Deleted Successfully.....");
					 }
					 else
					 {
						 System.out.println("Player Not Deleted.....");
					 }
					 break;
				case 4:
					System.out.println("Enter the Name of Player");
			        String uname=sc.next();
			        System.out.println("Enter the new Runs of Player");
			        int uruns=sc.nextInt();
			        int v=stmt.executeUpdate("update player set runs="+uruns+" where name='"+uname+"' ");
			        if(v>0)
			        {
			        	System.out.println("Data Updated Successfully...");
			        }
			        else
			        {
			        	System.out.println("Player Name is Not Found");
			        }
			        break;
				case 5:
					System.out.println("Enter the Player name for search");
					String sname=sc.next();
					ResultSet rs1=stmt.executeQuery("select *from player where name='"+sname+"' ");
					if(rs1.next())
					{
						System.out.println(rs1.getInt("pid")+"\t\t"+rs1.getString("name")+"\t\t"+rs1.getInt("runs"));
					}
					else
					{
						System.out.println("Player Name Not Found...");
					}
					break;
				} 
				
			}while(true);
			
		}
		else
		{
			System.out.println("Database Not Connected..");
		}
	}
}
