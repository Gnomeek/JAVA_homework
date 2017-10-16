package chap2_19;

public class Accessauthority {
    //private的属性，只有Accessauthority自己可以访问
    //子类不能继承
    //其他类也不能访问
    private int private_id;
    // public的属性
    // 自己可以访问 
    // 同包子类可以继承
    // 不同包子类可以继承 
    // 同包类可以访问
    // 不同包类可以访问 
    public String public_id;
    //属性hp是default的
    // 自己可以访问    
    // 同包子类可以继承
    // 不同包子类不能继承 
    // 同包类可以访问
    // 不同包类不能访问
    float default_id;
    // protected饰符的属性
    // 自己可以访问
    // 同包子类可以继承
    // 不同包子类可以继承 
    // 同包类可以访问
    // 不同包类不能访问
    protected float protected_id;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}