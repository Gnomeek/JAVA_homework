package chap2_21;
import java.lang.reflect.*;

interface interA{
	void m();
	String a();}

interface interB{
	void n();
	String  b();}

interface interC extends interA,interB{
	void c();}

class inherit{}

public class Interf extends inherit implements interC{
	public void m(){
		System.out.println("print m()");}
	public String a(){
		return "invoke a()";}
	public void n(){
		System.out.println("print c()");}
	public String  b(){
		return "invoke b()";}
	public void  c(){
		System.out.println("print c()");}
	public static void main(String[] args)throws Exception{
		Class<Interf> invokertester = Interf.class;
		Object newInstan = invokertester.newInstance();
		Method[] methods = invokertester.getDeclaredMethods();
		for(Method m:methods) {
			if(!"main".equals(m.getName())){
				if(m.getReturnType()==String.class){
					System.out.println(m.invoke(newInstan,new Object[]{}));}
				else{
					m.invoke(newInstan,new Object[]{});}}}}}