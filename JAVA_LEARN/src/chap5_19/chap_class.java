package chap5_19;


import java.lang.reflect.*;

public class chap_class {
	public static void main(String[] args){
		Class<chap_class> c=chap_class.class;
		System.out.println(c.getName());
		System.out.println(c.getSuperclass());
		System.out.println(Modifier.toString(c.getModifiers()));
		Type[] ifs=c.getInterfaces();
		if(ifs.length!=0)
		{
			for(Type inf :ifs)
				System.out.println(inf.toString());
		}else{
			System.out.println("No Interfaces");
		}
		
		Member[] mes=c.getMethods();
		if((mes.length!=0)){
			for(Member mef:mes){
				System.out.println(mef.toString());
			}
		}
			else{
				System.out.println("No Member");
			}
		}
	}

